package FarmStock.example.stock.Respository;

import FarmStock.example.stock.models.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface SaleRespository extends JpaRepository<Sale, Long> {

    // Get total quantity sold for a crop
    @Query("SELECT COALESCE(SUM(s.quantity), 0) " +
            "FROM Sale s WHERE s.crop.id = :cropId")
    double sumQuantityByCropId(@Param("cropId") Long cropId);

    // Get total quantity sold excluding the current sale
    // Used when updating a sale
    @Query("SELECT COALESCE(SUM(s.quantity), 0) " +
            "FROM Sale s " +
            "WHERE s.crop.id = :cropId AND s.id <> :saleId")
    double sumQuantityByCropIdExcludingSale(
            @Param("cropId") Long cropId,
            @Param("saleId") Long saleId);

    // Get total revenue for a crop within a date range
    @Query("SELECT COALESCE(SUM(s.totalRevenue), 0) " +
            "FROM Sale s " +
            "WHERE s.crop.id = :cropId " +
            "AND s.saleDate BETWEEN :startDate AND :endDate")
    double getRevenueByCropAndDateRange(
            @Param("cropId") Long cropId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);
}
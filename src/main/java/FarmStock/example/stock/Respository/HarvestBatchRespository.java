package FarmStock.example.stock.Respository;

import FarmStock.example.stock.models.HarvestBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HarvestBatchRespository extends JpaRepository<HarvestBatch, Long> {

    @Query("SELECT COALESCE(SUM(h.quantity), 0) FROM HarvestBatch h WHERE h.crop.id = :cropId")
    double sumQuantityByCropId(@Param("cropId") Long cropId);
}

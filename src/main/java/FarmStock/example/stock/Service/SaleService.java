package FarmStock.example.stock.Service;

import FarmStock.example.stock.Respository.HarvestBatchRespository;
import FarmStock.example.stock.Respository.SaleRespository;
import FarmStock.example.stock.models.Sale;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    private final SaleRespository saleRespository;
    private final HarvestBatchRespository harvestBatchRespository;

    public SaleService(SaleRespository saleRespository,
                       HarvestBatchRespository harvestBatchRespository) {
        this.saleRespository = saleRespository;
        this.harvestBatchRespository = harvestBatchRespository;
    }

    // Create Sale
    public Sale createSale(Sale sale) {

        checkStock(sale.getCrop().getId(), sale.getQuantity(), null);

        sale.setTotalRevenue(
                sale.getQuantity() * sale.getPricePerUnit()
        );

        return saleRespository.save(sale);
    }

    // Get all Sales
    public List<Sale> getAllSales() {
        return saleRespository.findAll();
    }

    // Get Sale by ID
    public Optional<Sale> getSaleById(Long id) {
        return saleRespository.findById(id);
    }

    // Update Sale
    public Sale updateSale(Sale sale) {

        if (sale.getId() == null) {
            throw new IllegalArgumentException("Sale ID is required for update");
        }

        checkStock(
                sale.getCrop().getId(),
                sale.getQuantity(),
                sale.getId()
        );

        sale.setTotalRevenue(
                sale.getQuantity() * sale.getPricePerUnit()
        );

        return saleRespository.save(sale);
    }

    // Delete Sale
    public void deleteSale(Long id) {
        saleRespository.deleteById(id);
    }

    // Check available stock
    private void checkStock(Long cropId, double saleQuantity, Long saleId) {

        double totalHarvested =
                harvestBatchRespository.sumQuantityByCropId(cropId);

        double totalSold;

        if (saleId == null) {
            totalSold =
                    saleRespository.sumQuantityByCropId(cropId);
        } else {
            totalSold =
                    saleRespository.sumQuantityByCropIdExcludingSale(
                            cropId, saleId
                    );
        }

        double availableStock = totalHarvested - totalSold;

        if (saleQuantity > availableStock) {
            throw new IllegalArgumentException(
                    "Insufficient stock. Available stock: " + availableStock
            );
        }
    }

    // Get available stock for a crop
    public double getAvailableStock(Long cropId) {

        double totalHarvested =
                harvestBatchRespository.sumQuantityByCropId(cropId);

        double totalSold =
                saleRespository.sumQuantityByCropId(cropId);

        return totalHarvested - totalSold;
    }

    // Get revenue for a crop between two dates
    public double getRevenueByCropAndDateRange(
            Long cropId,
            LocalDate startDate,
            LocalDate endDate) {

        return saleRespository.getRevenueByCropAndDateRange(
                cropId,
                startDate,
                endDate
        );
    }
}
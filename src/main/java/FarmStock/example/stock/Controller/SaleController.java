package FarmStock.example.stock.Controller;

import FarmStock.example.stock.Service.SaleService;
import FarmStock.example.stock.models.Sale;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/sale")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    // Create Sale
    @PostMapping("/create")
    public Sale createSale(@RequestBody Sale sale) {
        return saleService.createSale(sale);
    }

    // Get all Sales
    @GetMapping("/getall")
    public List<Sale> getAllSales() {
        return saleService.getAllSales();
    }

    // Get Sale by ID
    @GetMapping("/getbyid/{id}")
    public Sale getSaleById(@PathVariable Long id) {
        return saleService.getSaleById(id)
                .orElse(null);
    }

    // Update Sale
    @PutMapping("/update")
    public Sale updateSale(@RequestBody Sale sale) {
        return saleService.updateSale(sale);
    }

    // Delete Sale
    @DeleteMapping("/deletebyid/{id}")
    public String deleteSale(@PathVariable Long id) {
        saleService.deleteSale(id);
        return "Sale deleted successfully";
    }

    // Get available stock for a crop
    @GetMapping("/stock/{cropId}")
    public double getAvailableStock(@PathVariable Long cropId) {
        return saleService.getAvailableStock(cropId);
    }

    // Get revenue for a crop between two dates
    @GetMapping("/revenue/{cropId}")
    public double getRevenueByCropAndDateRange(
            @PathVariable Long cropId,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {

        return saleService.getRevenueByCropAndDateRange(
                cropId,
                startDate,
                endDate
        );
    }
}
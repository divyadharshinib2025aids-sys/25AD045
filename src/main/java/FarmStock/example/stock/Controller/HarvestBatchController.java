package FarmStock.example.stock.Controller;

import FarmStock.example.stock.Service.HarvestBatchServices;
import FarmStock.example.stock.models.HarvestBatch;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/harvestbatch")
public class HarvestBatchController {

    private final HarvestBatchServices harvestBatchService;

    public HarvestBatchController(HarvestBatchServices harvestBatchService) {
        this.harvestBatchService = harvestBatchService;
    }

    // Create Harvest Batch
    @PostMapping("/create")
    public HarvestBatch createHarvestBatch(@RequestBody HarvestBatch harvestBatch) {
        return harvestBatchService.createHarvestBatch(harvestBatch);
    }

    // Get all Harvest Batches
    @GetMapping("/getall")
    public List<HarvestBatch> getAllHarvestBatches() {
        return harvestBatchService.getAllHarvestBatches();
    }

    // Get Harvest Batch by ID
    @GetMapping("/getbyid/{id}")
    public HarvestBatch getHarvestBatchById(@PathVariable Long id) {
        return harvestBatchService.getHarvestBatchById(id)
                .orElse(null);
    }

    // Update Harvest Batch
    @PutMapping("/update")
    public HarvestBatch updateHarvestBatch(@RequestBody HarvestBatch harvestBatch) {
        return harvestBatchService.updateHarvestBatch(harvestBatch);
    }

    // Delete Harvest Batch
    @DeleteMapping("/deletebyid/{id}")
    public String deleteHarvestBatch(@PathVariable Long id) {
        harvestBatchService.deleteHarvestBatch(id);
        return "Harvest batch deleted successfully";
    }

    // Get total harvested quantity for a crop
    @GetMapping("/total/{cropId}")
    public double getTotalHarvestedQuantity(@PathVariable Long cropId) {
        return harvestBatchService.getTotalHarvestedQuantity(cropId);
    }
}
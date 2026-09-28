package FarmStock.example.stock.Service;

import FarmStock.example.stock.Respository.HarvestBatchRespository;
import FarmStock.example.stock.models.HarvestBatch;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HarvestBatchServices {

    private final HarvestBatchRespository harvestBatchRespository;

    public HarvestBatchServices(HarvestBatchRespository harvestBatchRespository) {
        this.harvestBatchRespository = harvestBatchRespository;
    }

    // Create Harvest Batch
    public HarvestBatch createHarvestBatch(HarvestBatch harvestBatch) {
        return harvestBatchRespository.save(harvestBatch);
    }

    // Get all Harvest Batches
    public List<HarvestBatch> getAllHarvestBatches() {
        return harvestBatchRespository.findAll();
    }

    // Get Harvest Batch by ID
    public Optional<HarvestBatch> getHarvestBatchById(Long id) {
        return harvestBatchRespository.findById(id);
    }

    // Update Harvest Batch
    public HarvestBatch updateHarvestBatch(HarvestBatch harvestBatch) {
        return harvestBatchRespository.save(harvestBatch);
    }

    // Delete Harvest Batch
    public void deleteHarvestBatch(Long id) {
        harvestBatchRespository.deleteById(id);
    }

    // Get total harvested quantity for a crop
    public double getTotalHarvestedQuantity(Long cropId) {
        return harvestBatchRespository.sumQuantityByCropId(cropId);
    }
}

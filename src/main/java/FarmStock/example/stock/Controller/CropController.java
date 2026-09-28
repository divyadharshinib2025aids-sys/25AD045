package FarmStock.example.stock.Controller;

import FarmStock.example.stock.Service.CropService;
import FarmStock.example.stock.models.Crop;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/crop")
public class CropController {

    private final CropService cropService;

    public CropController(CropService cropService) {
        this.cropService = cropService;
    }

    @PostMapping("/create")
    public Crop createCrop(@RequestBody Crop crop) {
        return cropService.createCrop(crop);
    }

    @GetMapping("/getall")
    public List<Crop> getAllCrops() {
        return cropService.getAllCrops();
    }

    @GetMapping("/getbyid/{id}")
    public Crop getCropById(@PathVariable Long id) {
        return cropService.getCropById(id).orElse(null);
    }

    @PutMapping("/update")
    public Crop updateCrop(@RequestBody Crop crop) {
        return cropService.updateCrop(crop);
    }

    @DeleteMapping("/deletebyid/{id}")
    public String deleteCrop(@PathVariable Long id) {
        cropService.deleteCrop(id);
        return "Crop deleted successfully";
    }
}
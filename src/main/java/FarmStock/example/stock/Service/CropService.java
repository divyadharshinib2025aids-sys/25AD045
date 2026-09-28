package FarmStock.example.stock.Service;

import FarmStock.example.stock.Respository.CropRespository;
import FarmStock.example.stock.models.Crop;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CropService {

    private final CropRespository cropRespository;

    public CropService(CropRespository cropRespository) {
        this.cropRespository = cropRespository;
    }

    public Crop createCrop(Crop crop) {
        return cropRespository.save(crop);
    }

    public List<Crop> getAllCrops() {
        return cropRespository.findAll();
    }

    public Optional<Crop> getCropById(Long id) {
        return cropRespository.findById(id);
    }

    public Crop updateCrop(Crop crop) {
        return cropRespository.save(crop);
    }

    public void deleteCrop(Long id) {
        cropRespository.deleteById(id);
    }
}
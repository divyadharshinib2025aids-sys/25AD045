package FarmStock.example.stock.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

@Entity
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "crop_id", nullable = false)
    @NotNull(message = "Crop is required")
    private Crop crop;

    @Positive(message = "Quantity must be greater than zero")
    private double quantity;

    @NotNull(message = "Sale date is required")
    private LocalDate saleDate;

    @Positive(message = "Price per unit must be greater than zero")
    private double pricePerUnit;

    private double totalRevenue;

    public Sale() {
    }

    public Sale(Crop crop, double quantity, LocalDate saleDate, double pricePerUnit) {
        this.crop = crop;
        this.quantity = quantity;
        this.saleDate = saleDate;
        this.pricePerUnit = pricePerUnit;
        this.totalRevenue = quantity * pricePerUnit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Crop getCrop() {
        return crop;
    }

    public void setCrop(Crop crop) {
        this.crop = crop;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
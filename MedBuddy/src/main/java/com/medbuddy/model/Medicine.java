package com.medbuddy.model;

import javax.persistence.*;

@Entity
@Table(name = "medicine")
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long medId;

    private String medName;
    private double medPrice;
    private String medImage;
    private String medUrl;
    private String manufacturer;  // New property
    private String description;   // New property

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Medicine() {

    }

    public Medicine(Long medId, String medName, double medPrice, String medImage, String medUrl,
                    String manufacturer, String description, Category category) {
        this.medId = medId;
        this.medName = medName;
        this.medPrice = medPrice;
        this.medImage = medImage;
        this.medUrl = medUrl;
        this.manufacturer = manufacturer;
        this.description = description;
        this.category = category;
    }

    public Long getMedId() {
        return medId;
    }

    public void setMedId(Long medId) {
        this.medId = medId;
    }

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public double getMedPrice() {
        return medPrice;
    }

    public void setMedPrice(double medPrice) {
        this.medPrice = medPrice;
    }

    public String getMedImage() {
        return medImage;
    }

    public void setMedImage(String medImage) {
        this.medImage = medImage;
    }

    public String getMedUrl() {
        return medUrl;
    }

    public void setMedUrl(String medUrl) {
        this.medUrl = medUrl;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Medicine [medId=" + medId + ", medName=" + medName + ", medPrice=" + medPrice + ", medImage=" + medImage
                + ", medUrl=" + medUrl + ", manufacturer=" + manufacturer + ", description=" + description
                + ", category=" + category + "]";
    }
}

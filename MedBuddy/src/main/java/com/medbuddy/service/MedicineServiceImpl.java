package com.medbuddy.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medbuddy.model.Medicine;
import com.medbuddy.repository.MedicineRepository;

@Service
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;

    @Override
    public List<Medicine> listAllMedicines() {
        return medicineRepository.findAll();
    }

    @Override
    public Medicine getMedicineById(Long id) {
        return medicineRepository.findById(id).orElse(null);
    }

    @Override
    public List<Medicine> listAllMedicinesInSortedOrder(String sortOrder) {
        if ("asc".equalsIgnoreCase(sortOrder)) {
            return medicineRepository.findByOrderByMedNameAsc();
        } else if ("desc".equalsIgnoreCase(sortOrder)) {
            return medicineRepository.findByOrderByMedNameDesc();
        } else {
            throw new IllegalArgumentException("Invalid sort order");
        }
    }

    @Override
    public List<Medicine> getMedicinesByCategory(String categoryName) {
        return medicineRepository.findByCategoryCategoryNameIgnoreCase(categoryName);
    }

    @Override
    public List<Medicine> searchMedicines(String keyword) {
        return medicineRepository.searchMedicines(keyword);
    }

    @Override
    public Medicine addNewMedicine(Medicine medicine) {
        return medicineRepository.save(medicine);
    }

    @Override
    public Medicine updateMedicine(Medicine medicine) {
        Long medId = medicine.getMedId();

        // Check if the medicine with the given ID exists in the database
        Medicine existingMedicine = medicineRepository.findById(medId)
                .orElseThrow(() -> new EntityNotFoundException("Medicine with ID " + medId + " not found"));

        // Update the fields of the existing medicine entity
        existingMedicine.setMedName(medicine.getMedName());
        existingMedicine.setMedPrice(medicine.getMedPrice());
        existingMedicine.setMedImage(medicine.getMedImage());
        existingMedicine.setMedUrl(medicine.getMedUrl());
        existingMedicine.setManufacturer(medicine.getManufacturer());
        existingMedicine.setDescription(medicine.getDescription());
        existingMedicine.setCategory(medicine.getCategory());

        return medicineRepository.save(existingMedicine);
    }


    @Override
    public void deleteMedicineById(Long id) {
        medicineRepository.deleteById(id);
    }
}

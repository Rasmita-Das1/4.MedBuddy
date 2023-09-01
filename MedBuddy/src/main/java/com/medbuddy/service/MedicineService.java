package com.medbuddy.service;

import java.util.List;

import com.medbuddy.model.Medicine;

public interface MedicineService {

    List<Medicine> listAllMedicines();
    
    Medicine getMedicineById(Long id);
    
    List<Medicine> listAllMedicinesInSortedOrder(String sortOrder);
    
    List<Medicine> getMedicinesByCategory(String categoryName);
    
    List<Medicine> searchMedicines(String keyword);
    
    Medicine addNewMedicine(Medicine medicine);
    
    Medicine updateMedicine(Medicine medicine);
    
    void deleteMedicineById(Long id);
}

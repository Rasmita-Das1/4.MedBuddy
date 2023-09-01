package com.medbuddy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.medbuddy.model.Medicine;
import com.medbuddy.service.MedicineService;

import java.util.List;

@Controller
@RequestMapping("/medicines")
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @GetMapping("/list")
    public String listAllMedicines(Model model) {
        List<Medicine> medicines = medicineService.listAllMedicines();
        model.addAttribute("medicines", medicines);
        return "medicine-list";
    }
    
    @GetMapping("/list-sorted")
    public String listAllMedicinesInSortedOrder(@RequestParam String order, Model model) {
        List<Medicine> medicines;
        if ("asc".equalsIgnoreCase(order)) {
            medicines = medicineService.listAllMedicinesInSortedOrder("asc");
        } else if ("desc".equalsIgnoreCase(order)) {
            medicines = medicineService.listAllMedicinesInSortedOrder("desc");
        } else {
            // Handle invalid order parameter
            return "redirect:/medicines/list";
        }
        
        model.addAttribute("medicines", medicines);
        return "medicine-list";
    }

    @GetMapping("/form")
    public String showMedicineForm(Model model) {
        model.addAttribute("medicine", new Medicine());
        return "medicine-form";
    }

    @GetMapping("/update-form/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Medicine medicine = medicineService.getMedicineById(id);
        if (medicine != null) {
            model.addAttribute("medicine", medicine);
            return "update-form";
        } else {
            // Handle case when medicine is not found
            return "redirect:/medicines/list";
        }
    }

    @PostMapping("/save")
    public String saveMedicine(@ModelAttribute("medicine") Medicine medicine) {
        if (medicine.getMedId() == null) {
            // New medicine, so save it
            medicineService.addNewMedicine(medicine);
        } else {
            // Existing medicine, so update it
            medicineService.updateMedicine(medicine);
        }
        return "redirect:/medicines/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteMedicine(@PathVariable Long id) {
        medicineService.deleteMedicineById(id);
        return "redirect:/medicines/list";
    }
}

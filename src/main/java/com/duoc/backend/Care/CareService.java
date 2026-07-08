package com.duoc.backend.Care;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CareService {

    @Autowired
    private CareRepository careRepository;

    // Obtener todos los servicios de cuidado
    public List<Care> getAllCares() {
        return (List<Care>) careRepository.findAll();
    }

    // Obtener un servicio de cuidado por ID
    public Care getCareById(Long id) {
        return careRepository.findById(id).orElse(null);
    }

    public Care saveCare(Care care) {
        return careRepository.save(care);
    }

    public Care updateCare(Long id, Care careDetails) {
        Care care = careRepository.findById(id).orElse(null);
        if (care == null) return null;
        care.setName(careDetails.getName());
        care.setCost(careDetails.getCost());
        return careRepository.save(care);
    }

    public void deleteCare(Long id) {
        careRepository.deleteById(id);
    }
}
package com.duoc.backend.Care;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/care")
public class CareController {

    @Autowired
    private CareService careService;

    @GetMapping
    public List<Care> getAllCares() {
        return careService.getAllCares();
    }

    @GetMapping("/{id}")
    public Care getCareById(@PathVariable Long id) {
        return careService.getCareById(id);
    }

    @PostMapping
    public Care saveCare(@RequestBody Care care) {
        return careService.saveCare(care);
    }

    @PutMapping("/{id}")
    public Care updateCare(@PathVariable Long id, @RequestBody Care care) {
        return careService.updateCare(id, care);
    }

    @DeleteMapping("/{id}")
    public void deleteCare(@PathVariable Long id) {
        careService.deleteCare(id);
    }
}
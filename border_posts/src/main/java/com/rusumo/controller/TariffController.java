/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rusumo.controller;

import com.rusumo.DTO.MultipleAccounts;
import com.rusumo.dto.DTOTally;
import com.rusumo.dto.MultipleTariff;
import com.rusumo.exception.ResourceNotFoundException;
import com.rusumo.models.Mdl_tariff;
import com.rusumo.models.Mdl_tariff;
import com.rusumo.repository.TariffRepository;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author SANGWA Emmanuel code [CODEGURU - info@codeguru.com]
 */
@RestController
@RequestMapping("/rusumo_warehouses/api/tariff")
@CrossOrigin("*")
@Slf4j
public class TariffController {

    @Autowired
    TariffRepository tariffRepository;

    @ApiOperation("Getting all the Account only")
    @GetMapping("/")

    public ResponseEntity<List<Mdl_tariff>> getAll() {
        List<Mdl_tariff> struc = new ArrayList<>();
        tariffRepository.findAll().forEach(struc::add);
        if (struc.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(struc, HttpStatus.OK);
    }

    @ApiOperation("Creating a tariff")
    @PostMapping("/")
    public ResponseEntity<Mdl_tariff> createtariff(@RequestBody @Valid Mdl_tariff mdl_tariff) {
        return new ResponseEntity<>(tariffRepository.save(mdl_tariff), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Updating  a single Structure")
    public ResponseEntity<Mdl_tariff> updateStructure(@PathVariable(value = "id") long id, @RequestBody Mdl_tariff mdl_tariff) {
        Mdl_tariff mdl_tariff1 = tariffRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("tariff not found"));
        mdl_tariff1.setId(mdl_tariff.getId());
        mdl_tariff1.setMax_days(mdl_tariff.getMax_days());
        mdl_tariff1.setPrice_per_kg(mdl_tariff.getPrice_per_kg());
        return new ResponseEntity<>(tariffRepository.save(mdl_tariff), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAccount(@PathVariable("id") long id) {
        tariffRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    Adding more items at the same time
    @PostMapping("/multiaccount")
    public ResponseEntity<String> multipleaccounts(@RequestBody MultipleTariff multipleTariffs) {
        List<Mdl_tariff> tariffsList = multipleTariffs.getMultiTariffs();
        try {
            for (Mdl_tariff mdl_tariff : tariffsList) {
                tariffRepository.save(mdl_tariff);
            }
            ResponseEntity<String> responseEntity = new ResponseEntity<>("Saved", HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
   

}

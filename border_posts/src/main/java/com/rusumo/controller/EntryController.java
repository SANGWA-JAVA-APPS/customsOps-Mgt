/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rusumo.controller;

import com.rusumo.dto.DTOEntry;
import com.rusumo.models.Mdl_client;
import com.rusumo.models.Mdl_entry;
import com.rusumo.print.controller.Commons;
import com.rusumo.repository.ClientRepository;
import com.rusumo.repository.EntryRepository;
import io.swagger.annotations.ApiOperation;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;

/**
 *
 * @author SANGWA Emmanuel code [CODEGURU - info@codeguru.com]
 */
@RestController
@RequestMapping("/rusumo_warehouses/api/entry")
@Slf4j
@CrossOrigin("*")

public class EntryController extends Commons {

    @Autowired
    EntryRepository entryRepository;
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    private ModelMapper modelMapper;

    @ApiOperation(value = "Retrieve all Entries")
    @PostMapping("/add/{clientId}")
    public ResponseEntity<Mdl_entry> createUser(@RequestBody @Valid Mdl_entry mdl_entry, @PathVariable(value = "clientId") long clientId) {
        Mdl_client mdl_client = clientRepository.findById(clientId).orElseThrow(() -> new ResourceAccessException("Id Not found"));
        mdl_client.setDateTime(new Commons().getCurrentDateTime());
        int year = Year.now().getValue();
        mdl_entry.setYear(year);
        mdl_entry.setMdl_client(mdl_client);
        return new ResponseEntity<>(entryRepository.save(mdl_entry), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get all Entries.")
    @GetMapping()
    ResponseEntity< List<Mdl_entry>> getentries() {
        List<Mdl_entry> allentries = new ArrayList<>();
        entryRepository.findAll().forEach(allentries::add);
        return new ResponseEntity<>(allentries, HttpStatus.OK);
    }

    @ApiOperation(value = "Retrieve all Entries")
    @GetMapping("/{startDate}/{endDate}")
    List<DTOEntry> getVehicle(@PathVariable(value = "startDate") String startDate,
            @PathVariable(value = "endDate") String endDate) {
        startDate = new Commons().getDateFromMills(startDate);
        endDate = new Commons().getDateFromMills(endDate);
        List<Mdl_entry> allentries = new ArrayList<>();
        entryRepository.findAllEntries(startDate, endDate).forEach(allentries::add);
        return allentries.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private DTOEntry convertToDto(Mdl_entry mdl_entry) {
        return modelMapper.map(mdl_entry, DTOEntry.class);
    }

}

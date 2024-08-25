/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rusumo.print.controller;

import com.rusumo.models.Mdl_tallying;
import com.rusumo.repository.ArrivalRepository;
import com.rusumo.repository.TallyingRepository;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.ResourceAccessException;

/**
 *
 * @author SANGWA Emmanuel code [CODEGURU - info@codeguru.com]
 */
@Controller
@RequestMapping("/export/pdf")
public class PdfArrivalController {

    @Autowired
    ArrivalRepository arrivalRepository;
    @Autowired
    TallyingRepository tallyingRepository;

    @GetMapping("/arrival/{entryId}")
    @ApiOperation(value = "Retrieve Arrival by an entry number")
    public List<Mdl_tallying> findarrivalByTin(@PathVariable(value = "entryId") long entryId) {
        List<Mdl_tallying> Arrival = tallyingRepository.findAll();
        return Arrival;
    }
}

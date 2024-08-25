package com.rusumo.controller;

import com.rusumo.exception.ResourceNotFoundException;
import com.rusumo.models.Mdl_inv_details;
import com.rusumo.DTO.MultipleInv_detailss;
import com.rusumo.models.Mdl_invoice;
import com.rusumo.models.Mdl_tariff;
import com.rusumo.repository.Inv_detailsRepository;
import com.rusumo.repository.InvoiceRepository;
import com.rusumo.repository.TariffRepository;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.client.ResourceAccessException;

/**
 *
 * @author SANGWA Emmanuel code [CODEGURU - info@codeguru.com]
 */
@RestController
@RequestMapping("/rusumo_warehouses/api/inv_details")
@CrossOrigin("*")
@Slf4j
public class Inv_detailsController {

    @Autowired
    Inv_detailsRepository inv_detailsRepository;

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    TariffRepository tariffRepository;

   
    
    @ApiOperation("Getting all the Inv_details only")
    @GetMapping("/")
    public ResponseEntity<List<Mdl_inv_details>> getAll() {
        List<Mdl_inv_details> struc = new ArrayList<>();
        inv_detailsRepository.findAll().forEach(struc::add);
        if (struc.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(struc, HttpStatus.OK);
    }

    @ApiOperation("Creating a structure")
    @PostMapping("/{invoiceId}/{tariffId}")
    public ResponseEntity<Mdl_inv_details> createStructure(@PathVariable(value = "invoiceId") long invoiceId,
            @PathVariable(value = "tariffId") long tariffId,
            @RequestBody @Valid Mdl_inv_details mdl_inv_details) {
        Mdl_invoice mdl_invoice = invoiceRepository.findById(invoiceId).orElseThrow(() -> new ResourceAccessException("Id Not found"));
        Mdl_tariff mdl_tariff = tariffRepository.findById(tariffId).orElseThrow(() -> new ResourceAccessException("Id Not found"));
        mdl_inv_details.setMdl_invoice(mdl_invoice);
        mdl_inv_details.setMdl_tariff(mdl_tariff);
        return new ResponseEntity<>(inv_detailsRepository.save(mdl_inv_details), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Updating  a single Structure")
    public ResponseEntity<Mdl_inv_details> updateStructure(@PathVariable(value = "id") long id, @RequestBody Mdl_inv_details mdl_inv_details) {
        Mdl_inv_details mdl_inv_details1 = inv_detailsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Structure not found"));
        mdl_inv_details1.setId(mdl_inv_details.getId());
        mdl_inv_details1.setItem(mdl_inv_details.getItem());
        mdl_inv_details1.setQuantity(mdl_inv_details.getQuantity());
        mdl_inv_details1.setWeight(mdl_inv_details.getWeight());
        mdl_inv_details1.setNo_days(mdl_inv_details.getNo_days());
        mdl_inv_details1.setCost(mdl_inv_details.getCost());
        mdl_inv_details1.setPaid_amnt(mdl_inv_details.getPaid_amnt());
        mdl_inv_details1.setRemaining(mdl_inv_details.getRemaining());
        mdl_inv_details1.setDate_time(mdl_inv_details.getDate_time());
        mdl_inv_details1.setStat_paid(mdl_inv_details.getStat_paid());
        mdl_inv_details1.setAccount_id(mdl_inv_details.getAccount_id());
        mdl_inv_details1.setDescription(mdl_inv_details.getDescription());
        return new ResponseEntity<>(inv_detailsRepository.save(mdl_inv_details), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteInv_details(@PathVariable("id") long id) {
        inv_detailsRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    Adding more items at the same time
    @PostMapping("/multiinv_details")
    public ResponseEntity<String> multipleinv_detailss(@RequestBody MultipleInv_detailss multipleInv_detailss) {
        List<Mdl_inv_details> inv_detailssList = multipleInv_detailss.getMultiInv_detailss();
        try {
            for (Mdl_inv_details mdl_inv_details : inv_detailssList) {
                inv_detailsRepository.save(mdl_inv_details);
            }
            ResponseEntity<String> responseEntity = new ResponseEntity<>("Saved", HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}

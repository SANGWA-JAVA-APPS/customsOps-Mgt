package com.rusumo.controller;

import com.rusumo.exception.ResourceNotFoundException;
import com.rusumo.models.Mdl_invoice;
import com.rusumo.DTO.MultipleInvoices;
import com.rusumo.models.Mdl_arrival;
import com.rusumo.repository.ArrivalRepository;
import com.rusumo.repository.InvoiceRepository;
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
import org.springframework.web.client.ResourceAccessException;

/**
 *
 * @author SANGWA Emmanuel code [CODEGURU - info@codeguru.com]
 */
@RestController
@RequestMapping("/rusumo_warehouses/api/invoice")
@CrossOrigin("*")
@Slf4j
public class InvoiceController {

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    ArrivalRepository arrivalRepository;

    @ApiOperation("Getting all the Invoice only")
    @GetMapping("/")
    public ResponseEntity<List<Mdl_invoice>> getAll() {
        List<Mdl_invoice> struc = new ArrayList<>();
        invoiceRepository.findAll().forEach(struc::add);
        if (struc.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(struc, HttpStatus.OK);
    }

    @ApiOperation("Creating a structure")
    @PostMapping("/{arrivalId}")
    public ResponseEntity<Mdl_invoice> createStructure(@PathVariable(name = "arrivalId") long arrivalId, @RequestBody @Valid Mdl_invoice mdl_invoice) {
        Mdl_arrival mdl_arrival = arrivalRepository.findById(arrivalId).orElseThrow(() -> new ResourceAccessException("Id Not found"));;
        mdl_invoice.setMdl_arrival(mdl_arrival);
        return new ResponseEntity<>(invoiceRepository.save(mdl_invoice), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Updating  a single Structure")
    public ResponseEntity<Mdl_invoice> updateStructure(@PathVariable(value = "id") long id, @RequestBody Mdl_invoice mdl_invoice) {
        Mdl_invoice mdl_invoice1 = invoiceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Structure not found"));
        mdl_invoice1.setId(mdl_invoice.getId());
        mdl_invoice1.setDate_time(mdl_invoice.getDate_time());
        return new ResponseEntity<>(invoiceRepository.save(mdl_invoice), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteInvoice(@PathVariable("id") long id) {
        invoiceRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    Adding more items at the same time
    @PostMapping("/multiinvoice")
    public ResponseEntity<String> multipleinvoices(@RequestBody MultipleInvoices multipleInvoices) {
        List<Mdl_invoice> invoicesList = multipleInvoices.getMultiInvoices();
        try {
            for (Mdl_invoice mdl_invoice : invoicesList) {
                invoiceRepository.save(mdl_invoice);
            }
            ResponseEntity<String> responseEntity = new ResponseEntity<>("Saved", HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}

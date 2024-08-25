package com.rusumo.controller;

import com.rusumo.exception.ResourceNotFoundException;
import com.rusumo.models.Mdl_receipt;
import com.rusumo.DTO.MultipleReceipts;
import com.rusumo.models.Mdl_invoice;
import com.rusumo.repository.InvoiceRepository;
import com.rusumo.repository.ReceiptRepository;
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
@RequestMapping("/rusumo_warehouses/api/receipt")
@CrossOrigin("*")
@Slf4j
public class ReceiptController {

    @Autowired
    ReceiptRepository receiptRepository;
    @Autowired
    InvoiceRepository invoiceRepository;

    @ApiOperation("Getting all the Receipt only")
    @GetMapping("/")
    public ResponseEntity<List<Mdl_receipt>> getAll() {
        List<Mdl_receipt> struc = new ArrayList<>();
        receiptRepository.findAll().forEach(struc::add);
        if (struc.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(struc, HttpStatus.OK);
    }

    @ApiOperation("Creating a structure")
    @PostMapping("/{invoiceId}")
    public ResponseEntity<Mdl_receipt> createStructure(@PathVariable(value = "invoiceId") long invoiceId ,@RequestBody @Valid Mdl_receipt mdl_receipt) {
        Mdl_invoice mdl_invoice = invoiceRepository.findById(invoiceId).orElseThrow(() -> new ResourceAccessException("Id Not found"));
        mdl_receipt.setMdl_invoice(mdl_invoice);
        return new ResponseEntity<>(receiptRepository.save(mdl_receipt), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Updating  a single Structure")
    public ResponseEntity<Mdl_receipt> updateStructure(@PathVariable(value = "id") long id, @RequestBody Mdl_receipt mdl_receipt) {
        Mdl_receipt mdl_receipt1 = receiptRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Structure not found"));
        mdl_receipt1.setId(mdl_receipt.getId());
        mdl_receipt1.setDate_time(mdl_receipt.getDate_time());
        return new ResponseEntity<>(receiptRepository.save(mdl_receipt), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteReceipt(@PathVariable("id") long id) {
        receiptRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    Adding more items at the same time
    @PostMapping("/multireceipt")
    public ResponseEntity<String> multiplereceipts(@RequestBody MultipleReceipts multipleReceipts) {
        List<Mdl_receipt> receiptsList = multipleReceipts.getMultiReceipts();
        try {
            for (Mdl_receipt mdl_receipt : receiptsList) {
                receiptRepository.save(mdl_receipt);
            }
            ResponseEntity<String> responseEntity = new ResponseEntity<>("Saved", HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}

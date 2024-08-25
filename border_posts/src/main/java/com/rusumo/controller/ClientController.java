package com.rusumo.controller;

import com.rusumo.exception.ResourceNotFoundException;
import com.rusumo.models.Mdl_client;
import com.rusumo.DTO.MultipleClients;
import com.rusumo.print.controller.Commons;
import com.rusumo.repository.ClientRepository;
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
@RequestMapping("/rusumo_warehouses/api/client")
@CrossOrigin("*")
@Slf4j
public class ClientController {

    @Autowired
    ClientRepository clientRepository;

    @ApiOperation("Getting all the Client only")
    @GetMapping("/")
    public ResponseEntity<List<Mdl_client>> getAll() {
        List<Mdl_client> struc = new ArrayList<>();
        clientRepository.findAll().forEach(struc::add);

        return new ResponseEntity<>(struc, HttpStatus.OK);
    }

    @ApiOperation("Getting a client by TIN")
    @GetMapping("/{tin}")
    public ResponseEntity<Mdl_client> getClientBytin(@PathVariable(value = "tin") String tin) {
        Mdl_client client = clientRepository.findClientByTin(tin);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @ApiOperation("Creating a client")
    @PostMapping("/")
    public ResponseEntity<Mdl_client> createStructure(@RequestBody @Valid Mdl_client mdl_client) {
        System.out.println("--------------We have inserted in the client -------------------------");

        mdl_client.setDateTime(new Commons().getCurrentDateTime());
        return new ResponseEntity<>(clientRepository.save(mdl_client), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Updating  a single Structure")
    public ResponseEntity<Mdl_client> updateStructure(@PathVariable(value = "id") long id, @RequestBody Mdl_client mdl_client) {
        Mdl_client mdl_client1 = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Structure not found"));
        mdl_client1.setId(mdl_client.getId());
        mdl_client1.setName(mdl_client.getName());
        mdl_client1.setSurname(mdl_client.getSurname());
        mdl_client1.setTelephone(mdl_client.getTelephone());
        mdl_client1.setTin(mdl_client.getTin());
        mdl_client1.setAddress(mdl_client.getAddress());
        mdl_client1.setDateTime(mdl_client.getDateTime());
        return new ResponseEntity<>(clientRepository.save(mdl_client), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteClient(@PathVariable("id") long id) {
        clientRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    Adding more items at the same time
    @PostMapping("/multiclient")
    public ResponseEntity<String> multipleclients(@RequestBody MultipleClients multipleClients) {
        List<Mdl_client> clientsList = multipleClients.getMultiClients();
        try {
            for (Mdl_client mdl_client : clientsList) {
                clientRepository.save(mdl_client);
            }
            ResponseEntity<String> responseEntity = new ResponseEntity<>("Saved", HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}

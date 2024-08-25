package com.rusumo.controller;

import com.rusumo.DTO.MultipleTallyings;
import com.rusumo.dto.DTOTally;
import com.rusumo.dto.Mdl_dates;
import com.rusumo.exception.ResourceNotFoundException;
import com.rusumo.models.Mdl_arrival;
import com.rusumo.models.Mdl_tallying;
import com.rusumo.print.controller.Commons;
import com.rusumo.repository.ArrivalRepository;
import com.rusumo.repository.TallyingRepository;
import com.rusumo.util.MyUtil;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
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
@RequestMapping("/rusumo_warehouses/api/tallying")
@CrossOrigin("*")
public class TallyingController {

    @Autowired
    TallyingRepository tallyingRepository;

    @Autowired
    ArrivalRepository arrivalRepository;

    @Autowired
    private ModelMapper modelMapper;

    @ApiOperation("Getting all the Tallying only")
    @PostMapping()
    public List<DTOTally> getAll(@RequestBody Mdl_dates mdl_dates) {
        Date d1 = new MyUtil().convertedDate(mdl_dates.getStartDate());
        Date d2 = new MyUtil().convertedDate(mdl_dates.getEndDate());
        System.out.println("------------------- The start date:" + d1 + " end date: " + d2 + " ------------------");
        List<Mdl_tallying> lstTallyings = new ArrayList<>();
        tallyingRepository.findAllTallyings(d1, d2).forEach(lstTallyings::add);
        return lstTallyings.stream().map(this::convertToDto).collect(Collectors.toList());

    }

//    @ApiOperation("Getting all the summerized Tallying")
//    @PostMapping("/summarized")
//    public ResponseEntity<List<Mdl_tallying>> getAllSummarized(@RequestBody Mdl_dates mdl_dates) {
//
//        Date d1 = new MyUtil().convertedDate(mdl_dates.getStartDate());
//        Date d2 = new MyUtil().convertedDate(mdl_dates.getEndDate());
//
//        System.out.println("-------summarized Tally ------------------" + d1 + " - " + d2);
//        List<Mdl_tallying> lstTallyings = new ArrayList<>();
//        tallyingRepository.findAll();
//
//        return new ResponseEntity<>(tallyingRepository.findAll(), HttpStatus.OK);
//    }
    @ApiOperation("Getting all the summerized Tallying")
    @PostMapping("/summarized")
    public ResponseEntity<List<Mdl_tallying>> getAllSummarized(@RequestBody Mdl_dates mdl_dates) {

        Date d1 = new MyUtil().convertedDate(mdl_dates.getStartDate());
        Date d2 = new MyUtil().convertedDate(mdl_dates.getEndDate());

        System.out.println("-------summarized Tally ------------------" + d1 + " - " + d2);
        List<Mdl_tallying> lstTallyings = new ArrayList<>();
        tallyingRepository.findSummarizedTallying(d1, d2).forEach(lstTallyings::add);

        return new ResponseEntity<>(tallyingRepository.findSummarizedTallying(d1, d2), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Mdl_tallying> getTallyById(@PathVariable(name = "id") long id) {
        Mdl_tallying mdl_tallying = tallyingRepository.findById(id).orElseThrow(() -> new ResourceAccessException("TallyId " + id + " Not found"));
        return new ResponseEntity<>(mdl_tallying, HttpStatus.OK);
    }

    @ApiOperation("Creating a Tally")
    @PostMapping("/{arrivalId}")
    public ResponseEntity<Mdl_tallying> createStructure(@RequestBody @Valid Mdl_tallying mdl_tallying, @PathVariable(value = "arrivalId") long arrivalId) {

        Mdl_arrival mdl_arrival = arrivalRepository.findById(arrivalId).orElseThrow(() -> new ResourceAccessException("Arrival with id " + arrivalId + " Not found"));

        System.out.println("-------------The arrival save is: " + arrivalId + " for container number: " + mdl_tallying.getCont_no() + " -----------------");
        mdl_tallying.setMdl_arrival(mdl_arrival);
        mdl_tallying.setDate_time(new Commons().currentDateTime());
        return new ResponseEntity<>(tallyingRepository.save(mdl_tallying), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/{arrivalId}")
    @ApiOperation(value = "Updating  a single Structure")
    public ResponseEntity<Mdl_tallying> updateStructure(@PathVariable(value = "id") long id, @PathVariable(value = "arrivalId") long arrivalId,
            @RequestBody Mdl_tallying mdl_tallying) {
        Mdl_tallying mdl_tallying1 = tallyingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Structure not found"));
        Mdl_arrival mdl_arrival = arrivalRepository.findById(id).orElseThrow(() -> new ResourceAccessException("Id Not found"));
        mdl_tallying1.setId(mdl_tallying.getId());
        mdl_tallying1.setItem_name(mdl_tallying.getItem_name());
        mdl_tallying1.setDate_time(mdl_tallying.getDate_time());
        mdl_tallying1.setAccount_id(mdl_tallying.getAccount_id());
        mdl_tallying1.setMdl_arrival(mdl_tallying.getMdl_arrival());
        mdl_tallying1.setDescription(mdl_tallying.getDescription());
        return new ResponseEntity<>(tallyingRepository.save(mdl_tallying), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTallying(@PathVariable("id") long id) {
        tallyingRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    Adding more items at the same time
    @PostMapping("/multitallying")
    public ResponseEntity<String> multipletallyings(@RequestBody MultipleTallyings multipleTallyings) {
        List<Mdl_tallying> tallyingsList = multipleTallyings.getMultiTallyings();
        try {
            for (Mdl_tallying mdl_tallying : tallyingsList) {
                tallyingRepository.save(mdl_tallying);
            }
            ResponseEntity<String> responseEntity = new ResponseEntity<>("Saved", HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private DTOTally convertToDto(Mdl_tallying mdl_tallying) {
        return modelMapper.map(mdl_tallying, DTOTally.class);
    }

}

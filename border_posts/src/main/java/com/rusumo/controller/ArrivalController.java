package com.rusumo.controller;

import com.rusumo.DTO.MultipleArrivals;
import com.rusumo.dto.DTO_ArrivalNotices;
import com.rusumo.dto.EntryArrival;
import com.rusumo.dto.Mdl_dates;
import com.rusumo.exception.ResourceNotFoundException;
import com.rusumo.models.Mdl_arrival;
import com.rusumo.models.Mdl_client;
import com.rusumo.models.Mdl_entry;
import com.rusumo.print.controller.Commons;
import com.rusumo.repository.ArrivalRepository;
import com.rusumo.repository.ClientRepository;
import com.rusumo.repository.EntryRepository;
import com.rusumo.repository.TallyingRepository;
import com.rusumo.util.MyUtil;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
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
@RequestMapping("/rusumo_warehouses/api/arrival")
@CrossOrigin("*")

public class ArrivalController {

    @Autowired
    ArrivalRepository arrivalRepository;

    @Autowired
    EntryRepository entryRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    TallyingRepository tallyingRepository;

    @ApiOperation("Getting all the Arrival only")
    @PostMapping("/{startDate}/{endDate}")
    public ResponseEntity<List<DTO_ArrivalNotices>> getAll(@PathVariable(value = "startDate") Date startDate, @PathVariable(value = "endDate") Date endDate) {

        List<DTO_ArrivalNotices> struc = new ArrayList<>();
        arrivalRepository.findEntriesArrival(startDate, endDate).forEach(struc::add);
        return new ResponseEntity<>(struc, HttpStatus.OK);
    }

    @ApiOperation("GEtting the arrival notice without converting the date, the date is converted from frontend")
    @PostMapping("/arrival/bydate/bypost")
    public ResponseEntity<List<DTO_ArrivalNotices>> getArrivalNoticeByDate(@RequestBody Mdl_dates mdl_dates) {
        List<DTO_ArrivalNotices> struc = new ArrayList<>();
        System.out.println("-----------" + mdl_dates.getStartDate() + "------------our dates----------------------------------: " + mdl_dates.getStartDate() + " -  -- " + mdl_dates.getEndDate());
        Date d1 = new MyUtil().convertedDate(mdl_dates.getStartDate());
        Date d2 = new MyUtil().convertedDate(mdl_dates.getEndDate());

        arrivalRepository.findEntriesArrival(d1, d2).forEach(struc::add);

        System.out.println("-----------------------size of the array: ---------------------------" + struc.size());
        return new ResponseEntity<>(struc, HttpStatus.OK);
    }

    @ApiOperation("Creating an arrival notice")
    @PostMapping("/{clientId}")
    public ResponseEntity<Mdl_arrival> createStructure(@RequestBody @Valid EntryArrival ea, @PathVariable(value = "clientId") Long clientId) {

        Mdl_client mdl_client = clientRepository.findById(clientId).orElseThrow(() -> new ResourceAccessException("Client with Id Not found: " + clientId));
        System.out.println(" -------------------------    Saving the arrival note   --------------------------------------");
        //Save the entry
        EntryArrival entryArrival1 = new EntryArrival();
        Mdl_entry mdl_entry = new Mdl_entry();

        mdl_entry.setAccount_id(ea.getAccount_id());
        mdl_entry.setCountry(ea.getCountry());
        mdl_entry.setDate_time(new Commons().getCurrentDateTime());
        mdl_entry.setDdcom(ea.getDdcom());
        mdl_entry.setDescripiion(ea.getDescripiion());
        mdl_entry.setPlate_no(ea.getPlate_no());
        mdl_entry.setStat_del(ea.getStat_del());
        mdl_entry.setStat_paid(ea.getStat_paid());
        mdl_entry.setStatus(ea.getStatus());
        mdl_entry.setYear(ea.getYear());
//
        mdl_entry.setClearing_agent(ea.getClearing_agent());
        mdl_entry.setCountry_dest(ea.getCountry_dest());
        mdl_entry.setYear(ea.getYear());
        mdl_entry.setMdl_client(mdl_client);
        entryRepository.save(mdl_entry);
        Mdl_arrival mdl_arrival = new Mdl_arrival();
        mdl_arrival.setSeal_no(ea.getSeal_no());
        mdl_arrival.setMdl_entry(mdl_entry);

        //Save the arrival
        return new ResponseEntity<>(arrivalRepository.save(mdl_arrival), HttpStatus.CREATED);

    }

    @ApiOperation("Get arrival notice by id")
    @GetMapping("/{arrivalId}")
    public ResponseEntity<Mdl_arrival> getAllByis(@PathVariable(value = "arrivalId") long arrivalId) {
        Mdl_arrival arrival = arrivalRepository.findById(arrivalId).orElseThrow(() -> new ResourceAccessException("Id " + arrivalId + " Not found"));
        return new ResponseEntity<>(arrival, HttpStatus.OK);
    }

    @PutMapping("/{id}/{clientId}")
    @ApiOperation(value = "Updating  a single Structure")
    public ResponseEntity<Mdl_arrival> updateStructure(@PathVariable(value = "id") long id, @PathVariable(value = "clientId") long clientId, @RequestBody EntryArrival ea) {
        //find the entryNo
        Mdl_entry mdl_entry = entryRepository.findById(ea.getId()).orElseThrow(() -> new ResourceAccessException("Entry Not found"));
        Mdl_client mdl_client = clientRepository.findById(clientId).orElseThrow(() -> new ResourceAccessException("Client Not found"));
        Mdl_arrival mdl_arrival1 = arrivalRepository.findById(ea.getId()).orElseThrow(() -> new ResourceNotFoundException("Arrival not found"));
        mdl_arrival1.setId(ea.getId());

        System.out.println("------------------------------------The arrival number id is: " + ea.getId());

        mdl_arrival1.setSeal_no(ea.getSeal_no());
        mdl_arrival1.setMdl_entry(mdl_entry);

        mdl_entry.setClearing_agent(ea.getClearing_agent());
        mdl_entry.setCountry(ea.getCountry());
        mdl_entry.setCountry_dest(ea.getCountry_dest());
        mdl_entry.setCountry_dest(ea.getCountry_dest());
        mdl_entry.setDdcom(ea.getDdcom());
        mdl_entry.setDescripiion(ea.getDescripiion());
        mdl_entry.setPlate_no(ea.getPlate_no());
        mdl_entry.setMdl_client(mdl_client);

        return new ResponseEntity<>(arrivalRepository.save(mdl_arrival1), HttpStatus.OK
        );

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteArrival(@PathVariable("id") long id) {
        arrivalRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Adding more items at the same time
    @PostMapping("/multiarrival")
    public ResponseEntity<String> multiplearrivals(@RequestBody MultipleArrivals multipleArrivals) {
        List<Mdl_arrival> arrivalsList = multipleArrivals.getMultiArrivals();
        try {
            for (Mdl_arrival mdl_arrival : arrivalsList) {
                arrivalRepository.save(mdl_arrival);
            }
            ResponseEntity<String> responseEntity = new ResponseEntity<>("Saved", HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}

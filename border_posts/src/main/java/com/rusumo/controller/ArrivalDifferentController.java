/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rusumo.controller;

import com.rusumo.dto.EntryArrival;
import com.rusumo.models.Mdl_arrival;
import com.rusumo.models.Mdl_client;
import com.rusumo.models.Mdl_entry;
import com.rusumo.print.controller.Commons;
import com.rusumo.repository.ArrivalRepository;
import com.rusumo.repository.ClientRepository;
import com.rusumo.repository.EntryRepository;
import com.rusumo.repository.TallyingRepository;
import io.swagger.annotations.ApiOperation;
import java.time.Year;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;

/**
 *
 * @author HP
 */
@RestController
@RequestMapping("/rusumo_warehouses/api/diffpage/")
@CrossOrigin("*")
public class ArrivalDifferentController {

    @Autowired
    ArrivalRepository arrivalRepository;

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    EntryRepository entryRepository;

    @Autowired
    TallyingRepository tallyingRepository;

    @ApiOperation("Creating an arrival notice")
    @PostMapping("/{clientId}")
    public ResponseEntity<Mdl_arrival> createStructure(@RequestBody @Valid EntryArrival ea, @PathVariable(value = "clientId") Long clientId) {
        long newIdGen = 0;
        String currentYear = String.valueOf(Year.now().getValue());
        Mdl_arrival mdl_arrival = new Mdl_arrival();
        if (arrivalRepository.count() < 1) {
            newIdGen = Long.valueOf(currentYear + "" + 1);
        } else {
            String lastDate = entryRepository.getLastEntry().getDate_time();
            System.out.println("--------------------------last Entry date:" + lastDate + "---------------------------------");
            String lastArrivalyear = lastDate.split("-")[0];
            int yearLength = currentYear.length();
            if (!currentYear.equals(lastArrivalyear)) {//we are stating the new year
                newIdGen = Long.parseLong(currentYear + "" + 1);
                System.out.println("--------------------------Starting year in id:" + newIdGen + "---------------------------------");
            } else if (currentYear.equals(lastArrivalyear)) {//we are on a ongoing year

                System.out.println("--------------------------There is an entry done in " + currentYear + "---------------------");
                System.out.println("--------------------------current year:" + currentYear + "-----------------------------lastArrivalyear: " + lastArrivalyear + "----");
                String LastArrivalid = String.valueOf(arrivalRepository.getLastArrival().getId()); //this is a combination of year and ariival id
                System.out.println("--------------------------Year length:" + yearLength + "-------------------LastArrivalid: " + LastArrivalid + "--------------");

                String YearSubstring = String.valueOf(LastArrivalid).substring(0, yearLength); // this is to retrieve the year from the whole 'Year_ArrivalId' string
                System.out.println("--------------------------YearSubstring from : " + LastArrivalid + "--------------------is " + YearSubstring + "-------------------//this is to retrieve the year from the whole 'Year_ArrivalId' string");

                String ArrivalDbIndex = String.valueOf(LastArrivalid).substring(yearLength, LastArrivalid.length()); // this is to subtract the true arrilvall id

                System.out.println("--------------------------Ongoing year in id:" + YearSubstring + "---------------------------------");
                String newId = YearSubstring + "" + (Long.parseLong(ArrivalDbIndex) + 1);
                newIdGen = Long.valueOf(newId);
            }
        }
        if (newIdGen > 0) {
            System.out.println("----------------------------------We have already saved the entry---------------------------------------");
            Mdl_client mdl_client = clientRepository.findById(clientId).orElseThrow(() -> new ResourceAccessException("Client with Id Not found: " + clientId));
//        System.out.println(" -------------------------    Saving the arrival note   --------------------------------------");
//        //Save the entry
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

            //<editor-fold defaultstate="collapsed" desc="-------------------------Entry ">
            mdl_entry.setClearing_agent(ea.getClearing_agent());
            mdl_entry.setCountry_dest(ea.getCountry_dest());
            mdl_entry.setYear(ea.getYear());
            mdl_entry.setMdl_client(mdl_client);
            entryRepository.save(mdl_entry);
            //</editor-fold>

            mdl_arrival.setId(newIdGen);
            mdl_arrival.setSeal_no(ea.getSeal_no());
            mdl_arrival.setMdl_entry(mdl_entry);
        }

        //Save the arrival
        return new ResponseEntity<>(arrivalRepository.save(mdl_arrival), HttpStatus.CREATED);

    }

}

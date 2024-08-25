/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rusumo.controller;

import com.rusumo.dto.DTOTally;
import com.rusumo.models.Mdl_tallying;
import com.rusumo.repository.TallyingRepository;
import java.time.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Administrator
 */
@Controller
@RequestMapping("/rusumo_warehouses/api/range")
public class ReportController extends ReportTotals {

    @Autowired
    TallyingRepository tallyingRepository;

    @GetMapping("/get3months")
    public ResponseEntity<Map<String, Object>> get3monthsbackFromNow() {
        Map<String, Object> All_lists = new HashMap<>();
        
        //Dates
        LocalDate currentdate = LocalDate.now();
        int month = currentdate.getMonthValue();
        int year =  LocalDate.now().getYear();
        int year_1= (month == 1) ? LocalDate.now().getYear() - 1 : LocalDate.now().getYear();
        int month_min1 = month - 1;
        int month_min2 = month - 2;

        //Lists
        List<DTOTally> currentMonthList = tallyingRepository.findCurrentMonthTallying(month, year);      //the lis tof the current month
        List<DTOTally> prev_1_MonthList = tallyingRepository.findCurrentMonthTallying(month_min1, year_1);    // the lis tofthe previous -1
        List<DTOTally> prev_2_MonthList = tallyingRepository.findCurrentMonthTallying(month_min2, year_1);    // the lis tofthe previous -2
        System.out.println("----------------APRIL-------------------");
        prev_1_MonthList.forEach(d -> System.out.println(d.getId() + "--" + d.getActual_wgh()));
        double CurrentoffloadedWeight = 0.0;
        double prev_1_offloadedWeight = 0.0;
        double prev_2_offloadedWeight = 0.0;

        CurrentoffloadedWeight = super.ActualWegihtTotal(currentMonthList, CurrentoffloadedWeight);
        prev_1_offloadedWeight = super.ActualWegihtTotal(prev_1_MonthList, prev_1_offloadedWeight);
        prev_2_offloadedWeight = super.ActualWegihtTotal(prev_2_MonthList, prev_2_offloadedWeight);

        //Assign lists
        ArrayList<Integer> range = new ArrayList<>();
        range.add(month);
        range.add(month_min1);
        range.add(month_min2);
        range.add(year);

        All_lists.put("range", range);
        All_lists.put("currentList", currentMonthList);
        All_lists.put("prevList_1", prev_1_MonthList);
        All_lists.put("prevList_2", prev_2_MonthList);

        //Totals
        All_lists.put("currOffloadedTotWght", CurrentoffloadedWeight);
        All_lists.put("prev_1_OffloadedTotWght", prev_1_offloadedWeight);
        All_lists.put("prev_2_OffloadedTotWght", prev_2_offloadedWeight);
        return new ResponseEntity<>(All_lists, HttpStatus.OK);

    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rusumo.controller;

import com.rusumo.dto.DTOTally;
import com.rusumo.models.Mdl_tallying;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class ReportTotals {

    public double ActualWegihtTotal(List<DTOTally> currentMonthList, double currentTotals) throws NumberFormatException {
        for (DTOTally mdl_tallying : currentMonthList) {
            if (true) {
                
            }
            currentTotals += Double.parseDouble(mdl_tallying.getActual_wgh());
        }
        return currentTotals;
    }
    

}

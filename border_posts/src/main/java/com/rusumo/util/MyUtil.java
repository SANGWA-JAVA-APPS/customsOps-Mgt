/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rusumo.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Data;

/**
 *
 * @author HP
 */
@Data
public class MyUtil {

    DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private String currentDateTime = dateFormatter.format(new Date());

    DateFormat dateFormatter2 = new SimpleDateFormat("yyyy-MM-dd");
    private String currentDate = dateFormatter2.format(new Date());

    public Date convertedDate(String givenDate) {

        Date d = new Date();
        try {
            SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd");
            d = DateFor.parse(givenDate);
        } catch (ParseException ex) {
            Logger.getLogger(MyUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d;
    }

    //compare two data
    // last arrivalid date
    //current year
    
}

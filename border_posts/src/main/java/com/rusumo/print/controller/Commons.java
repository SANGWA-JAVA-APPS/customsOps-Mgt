/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rusumo.print.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import lombok.Data;

/**
 *
 * @author SANGWA Emmanuel code [CODEGURU - info@codeguru.com]
 */
@Data
public class Commons {

    DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String currentDateTime = dateFormatter.format(new Date());

    DateFormat dateFormatter2 = new SimpleDateFormat("yyyy-MM-dd");
    String currentDate = dateFormatter2.format(new Date());

  public  String getDateFromMills(String mills) {
        long milliSec = Long.parseLong(mills);
        //convert milliseconds into Date by creating an object of the date class and passing milliseconds to the constructor   
        Date res = new Date(milliSec);
        //printing value of Date(default format)   
        System.out.println("After converting milliseconds into date: " + res);
        // create an instance of the SimpleDateFormat class for modifying date object into dd:MM:yy:HH:mm:ss and dd MMM yyyy HH:mm:ss:SSS Z format   
        DateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat sdf2 = new SimpleDateFormat("dd MMM yyyy HH:mm:ss:SSS Z");
        //after formatting the res into dd:MM:yy:HH:mm:ss and dd MMM yyyy HH:mm:ss:SSS Z format   
        System.out.println("Date in dd:MM:yy:HH:mm:ss: " + sdf1.format(res));
        System.out.println("Date in dd MMM yyyy HH:mm:ss:SSS Z: " + sdf2.format(res));
        //convert milliseconds into Date by using calendar class   
        // create instance of the Calendar class   
        Calendar calndr = Calendar.getInstance();
        // use setTimeInMillis() method od the calendar class to set time    
        calndr.setTimeInMillis(milliSec);
        // after formatting the date into dd:MM:yy:HH:mm:ss   
        System.out.println("After converting milliseconds into date using Calendar class: " + sdf1.format(calndr.getTime()));
        //copying data of one date into another one   
        Date currentdate = new Date();
        Date tempDate = new Date(currentdate.getTime());
        System.out.println("original Date: " + sdf1.format(currentdate));
//       System.out.println("copied Date: " + sdf1.format(tempDate)); 

        return sdf1.format(currentdate);
    }

    public String currentDateTime() {
        return currentDateTime;
    }
}

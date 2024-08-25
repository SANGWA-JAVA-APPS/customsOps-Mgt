/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rusumo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author HP
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DTO_ArrivalNotices {

    //ARRIVAL
    private long id;
    private String seal_no;

   //Client
    private String name;
    private String surname;
    private String telephone;
    private String tin;
    //Entry
    private String country;
    private String ddcom;
    private String plate_no;
    private String clearing_agent;
    private String country_dest;
    private String date_time;

}

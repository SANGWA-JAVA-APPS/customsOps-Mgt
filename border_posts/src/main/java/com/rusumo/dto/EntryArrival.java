/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.rusumo.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author SANGWA Emmanuel code [CODEGURU - info@codeguru.com]
 */
 
@Getter
@Setter
public class EntryArrival {
    
    private long id;
    private String date_time;
    private int year;
    private String plate_no;
    private String ddcom;
    private String country;
    private String status;
    private String stat_paid;
    private String stat_del;
    private String descripiion;
    private String account_id;
    private String country_dest;
    private String clearing_agent;
    private String seal_no;


}

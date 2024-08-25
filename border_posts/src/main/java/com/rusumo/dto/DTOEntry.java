/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rusumo.dto;

import lombok.Data;

/**
 *
 * @author Administrator
 */
@Data
public class DTOEntry {
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
    private String clearing_agent;
    private String country_dest;
    private String account_id;
}
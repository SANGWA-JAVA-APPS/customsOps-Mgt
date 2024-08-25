/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rusumo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author HP
 */
@Getter
@Setter
@NoArgsConstructor
public class Mdl_dates {

    private String startDate;
    private String endDate;

    public Mdl_dates(String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

}

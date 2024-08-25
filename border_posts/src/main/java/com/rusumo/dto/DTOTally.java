/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rusumo.dto;

import com.rusumo.models.Mdl_arrival;
import com.rusumo.models.Mdl_client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOTally {
        private long id;
        private String item_name;
        private String date_time;
        private String description;
        private String man_qty;
        private String man_weight;
        private String actual_qty;
        private String actual_wgh;
        private String cont_no;
        private String account_id;
        private String name;
        private String surname;
        private String tin;
        //arrival 
        private String seal_no;
        private long arriv_no;
}

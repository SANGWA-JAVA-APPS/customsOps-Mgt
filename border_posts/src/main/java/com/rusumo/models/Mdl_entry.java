/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rusumo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Entity
@Table(name = "entry")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Mdl_entry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Size(min = 1, max = 20, message = " date_time should not be empty, null and or length exceed 20")
    @Column(name = "date_time", length = 120, nullable = false)
    private String date_time;

    @Size(min = 1, max = 120, message = " year should not be empty, null and or length exceed 30")
    @Column(name = "year", length = 120, nullable = false)
    private int year;

    @Size(min = 1, max = 100, message = " plate_no should not be empty, null and or length exceed 100")
    @Column(name = "plate_no", length = 100, nullable = false)
    private String plate_no;

    @Size(min = 1, max = 120, message = " ddcom should not be empty, null and or length exceed 30")
    @Column(name = "ddcom", length = 120, nullable = false)
    private String ddcom;

    @Size(min = 1, max = 120, message = " country should not be empty, null and or length exceed 30")
    @Column(name = "country", length = 120, nullable = false)
    private String country;

    @Size(min = 1, max = 120, message = " status should not be empty, null and or length exceed 30")
    @Column(name = "status", length = 120, nullable = false)
    private String status;

    @Size(min = 1, max = 120, message = " stat_paid should not be empty, null and or length exceed 30")
    @Column(name = "stat_paid", length = 120, nullable = false)
    private String stat_paid;

    @Size(min = 1, max = 120, message = " stat_del should not be empty, null and or length exceed 30")
    @Column(name = "stat_del", length = 120, nullable = false)
    private String stat_del;

    @Size(min = 1, max = 150, message = " descripiion should not be empty, null and or length exceed 30")
    @Column(name = "descripiion", length = 150, nullable = false)
    private String descripiion;
    
    @Size(min = 1, max = 150, message = " clearing_agent should not be empty, null and or length exceed 150")
    @Column(name = "clearing_agent", length = 150, nullable = false)
    private String clearing_agent;
    
    @Size(min = 1, max = 150, message = " country_dest should not be empty, null and or length exceed 150")
    @Column(name = "country_dest", length = 150, nullable = false)
    private String country_dest;


    @Size(min = 1, max = 100, message = " account_id should not be empty, null and or length exceed  100")
    @Column(name = "account_id", length = 100, nullable = false)
    private String account_id;

    @ManyToOne
    @JoinColumn(name = "client_entry")
    private Mdl_client mdl_client;

    @OneToMany(mappedBy = "mdl_entry")
    @JsonIgnoreProperties("mdl_entry")
    private List<Mdl_arrival> o_arriv;

}

package com.rusumo.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "rec_details")
@Getter @Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Mdl_rec_details implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 1, max = 20, message = " mod_pay should not be empty, null and or length exceed 30")
    @Column(name = "mod_pay", length = 20, nullable = false)
    private String mod_pay;

    @Size(min = 1, max = 20, message = " amt_paid should not be empty, null and or length exceed 30")
    @Column(name = "amt_paid", length = 20, nullable = false)
    private String amt_paid;

    @Size(min = 1, max = 20, message = " description should not be empty, null and or length exceed 30")
    @Column(name = "description", length = 20, nullable = false)
    private String description;

    @Size(min = 1, max = 100, message = " account_id should not be empty, null and or length exceed 30")
    @Column(name = "account_id", length = 100, nullable = false)
    private String account_id;
    
    @ManyToOne
    @JoinColumn(name = "rec_recdetails")
    private Mdl_receipt mdl_receipt;
  

}

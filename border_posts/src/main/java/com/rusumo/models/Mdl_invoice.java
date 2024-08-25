package com.rusumo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "invoice")
@Getter @Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Mdl_invoice implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 1, max = 20, message = " date_time should not be empty, null and or length exceed 30")
    @Column(name = "date_time", length = 20, nullable = false)
    private String date_time;

    @Size(min = 1, max = 100, message = " account_id should not be empty, null and or length exceed 30")
    @Column(name = "account_id", length = 100, nullable = false)
    private String account_id;

    @ManyToOne
    @JoinColumn(name = "arriv_invoice")
    private Mdl_arrival mdl_arrival;

    @OneToMany(mappedBy = "mdl_invoice")
    @JsonIgnoreProperties("mdl_invoice")
    private List<Mdl_inv_details> o_inv_details;
  
    @OneToMany(mappedBy = "mdl_invoice")
    @JsonIgnoreProperties("mdl_invoice")
    private List<Mdl_receipt> o_inv_receipt;
     
    
   
}

package com.rusumo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "inv_details")
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Mdl_inv_details implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 1, max = 20, message = " item should not be empty, null and or length exceed 30")
    @Column(name = "item", length = 20, nullable = false)
    private Integer item;

    @Size(min = 1, max = 20, message = " quantity should not be empty, null and or length exceed 30")
    @Column(name = "quantity", length = 20, nullable = false)
    private Integer quantity;

    @Size(min = 1, max = 20, message = " weight should not be empty, null and or length exceed 30")
    @Column(name = "weight", length = 20, nullable = false)
    private String weight;

    @Size(min = 1, max = 20, message = " no_days should not be empty, null and or length exceed 30")
    @Column(name = "no_days", length = 20, nullable = false)
    private Integer no_days;

    @Size(min = 1, max = 20, message = " cost should not be empty, null and or length exceed 30")
    @Column(name = "cost", length = 20, nullable = false)
    private String cost;

    @Size(min = 1, max = 20, message = " paid_amnt should not be empty, null and or length exceed 30")
    @Column(name = "paid_amnt", length = 20, nullable = false)
    private String paid_amnt;

    @Size(min = 1, max = 20, message = " remaining should not be empty, null and or length exceed 30")
    @Column(name = "remaining", length = 20, nullable = false)
    private String remaining;

    @Size(min = 1, max = 20, message = " date_time should not be empty, null and or length exceed 30")
    @Column(name = "date_time", length = 20, nullable = false)
    private String date_time;

    @Size(min = 1, max = 20, message = " stat_paid should not be empty, null and or length exceed 30")
    @Column(name = "stat_paid", length = 20, nullable = false)
    private String stat_paid;

   
    @Size(min = 1, max = 20, message = " description should not be empty, null and or length exceed 30")
    @Column(name = "description", length = 20, nullable = false)
    private String description;

    @Size(min = 1, max = 100, message = " account should not be empty, null and or length exceed 30")
    @Column(name = "account_id", length = 100, nullable = false)
    private String account_id;

    @ManyToOne
    @JoinColumn(name = "inv_invdetails")
    @JsonIgnore
    private Mdl_invoice mdl_invoice;

    @ManyToOne
    @JoinColumn(name = "tariff_id")
    private Mdl_tariff mdl_tariff;

}

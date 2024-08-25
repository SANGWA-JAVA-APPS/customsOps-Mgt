package com.rusumo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "client")
@Getter @Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Mdl_client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 1, max = 180, message = " name should not be empty, null and or length exceed 180")
    @Column(name = "name", length = 180, nullable = false)
    private String name;

    @Size(min = 1, max = 20, message = " surname should not be empty, null and or length exceed 30")
    @Column(name = "surname", length = 20, nullable = false)
    private String surname;

    @Size(min = 1, max = 20, message = " telephone should not be empty, null and or length exceed 30")
    @Column(name = "telephone", length = 20, nullable = false)
    private String telephone;

    @Size(min = 1, max = 20, message = " tin should not be empty, null and or length exceed 30")
    @Column(name = "tin", length = 20, nullable = false)
    private String tin;

    @Size(min = 1, max = 20, message = " address should not be empty, null and or length exceed 30")
    @Column(name = "address", length = 20, nullable = false)
    private String address;

    @Size(min = 1, max = 20, message = " dateTime should not be empty, null and or length exceed 30")
    @Column(name = "dateTime", length = 20, nullable = true)
    private String dateTime;

    @Size(min = 1, max = 100, message = " account_id should not be empty, null and or length exceed 30")
    @Column(name = "account_id", length = 100, nullable = false)
    private String account_id;
    
    @OneToMany(mappedBy = "mdl_client")
    @JsonIgnoreProperties("mdl_client")
    private List<Mdl_entry> o_entries;



}

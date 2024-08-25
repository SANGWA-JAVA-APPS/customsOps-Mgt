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
@Table(name = "profile")
@Getter @Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Mdl_profile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
 
    @Size(min = 1, max = 20, message = " name should not be empty, null and or length exceed 30")
    @Column(name = "name", length = 20, nullable = false)
    private String name;

    @Size(min = 1, max = 20, message = " surname should not be empty, null and or length exceed 30")
    @Column(name = "surname", length = 20, nullable = false)
    private String surname;

    @Size(min = 1, max = 30, message = " date_birth should not be empty, null and or length exceed 30")
    @Column(name = "date_birth", length = 30, nullable = false)
    private Date date_birth;

    @Size(min = 1, max = 20, message = " gender should not be empty, null and or length exceed 30")
    @Column(name = "gender", length = 20, nullable = false)
    private String gender;

    @Size(min = 1, max = 20, message = " tel should not be empty, null and or length exceed 30")
    @Column(name = "tel", length = 20, nullable = false)
    private String tel;

    @Size(min = 1, max = 20, message = " email should not be empty, null and or length exceed 30")
    @Column(name = "email", length = 20, nullable = false)
    private String email;

    @Size(min = 1, max = 20, message = " residence should not be empty, null and or length exceed 30")
    @Column(name = "residence", length = 20, nullable = false)
    private String residence;

    @Size(min = 1, max = 20, message = " image should not be empty, null and or length exceed 30")
    @Column(name = "image", length = 20, nullable = false)
    private Integer image;

  

}

package com.rusumo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "arrival")
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Mdl_arrival implements Serializable {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", insertable = true, updatable = true, unique = true, nullable = false)
    private long id;

    @OneToMany(mappedBy = "mdl_arrival")
    @JsonIgnoreProperties("mdl_arrival")
    private List<Mdl_invoice> o_invoices;

    @Size(min = 1, max = 120, message = " seal_no should not be empty, null and or length exceed 120")
    @Column(name = "seal_no", length = 120, nullable = false)
    private String seal_no;

    @ManyToOne
    @JoinColumn(name = "entry_arrival")
    Mdl_entry mdl_entry;//here it should be one but i dont know how to make it, one arrival is one entry actually

    @OneToMany(mappedBy = "mdl_arrival")
    @JsonIgnoreProperties("mdl_arrival")
    private List<Mdl_tallying> o_tallyings;
}

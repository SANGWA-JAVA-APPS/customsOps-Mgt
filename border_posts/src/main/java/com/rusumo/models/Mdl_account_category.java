package com.rusumo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import javax.validation.constraints.Size;
import java.io.Serializable;
import org.hibernate.annotations.Filter;


@Entity
@Table(name = "account_category")
@Data
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Mdl_account_category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 1, max = 20, message = " name should not be empty, null and or length exceed 20")
    @Column(name = "name", length = 20, nullable = false)
    private String name;
    
    @OneToMany(mappedBy = "mdl_account_category")
    @JsonIgnoreProperties("mdl_account_category")
    private List<Mdl_account> o_accounts;

}

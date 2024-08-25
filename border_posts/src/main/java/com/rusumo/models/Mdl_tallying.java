package com.rusumo.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "tallying")
@Getter @Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class Mdl_tallying implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 1, max = 150, message = " item_name should not be empty, null and or length exceed 30")
    @Column(name = "item_name", length = 150, nullable = false)
    private String item_name;

    @Size(min = 1, max = 20, message = " date_time should not be empty, null and or length exceed 30")
    @Column(name = "date_time", length = 20, nullable = false)
    private String date_time;

    @Size(min = 1, max = 150, message = " description should not be empty, null and or length exceed 30")
    @Column(name = "description", length = 150, nullable = false)
    private String description;
    
    @Size(min = 1, max = 100, message = " man_qty should not be empty, null and or length exceed 100")
    @Column(name = "man_qty", length = 100, nullable = false)
    private String man_qty;
    
    @Size(min = 1, max = 100, message = " man_qty should not be empty, null and or length exceed 100")
    @Column(name = "man_weight", length = 100, nullable = false)
    private String man_weight;
    
    @Size(min = 1, max = 100, message = "actual_qty should not be empty, null and or length exceed 100")
    @Column(name = "actual_qty", length = 100, nullable = false)
    private String actual_qty;
    
    @Size(min = 1, max = 100, message = "actual_wgh should not be empty, null and or length exceed 100")
    @Column(name = "actual_wgh", length = 100, nullable = false)
    private String actual_wgh;
    
    @Size(min = 1, max = 100, message = "cont_no should not be empty, null and or length exceed 100")
    @Column(name = "cont_no", length = 100, nullable = false)
    private String cont_no;
    
    @Size(min = 1, max = 100, message = " account_id should not be empty, null and or length exceed 30")
    @Column(name = "account_id", length = 100, nullable = false)
    private String account_id;
 
    @ManyToOne
    @JoinColumn(name = "arriv_tallying", insertable = true, updatable = true)
    @NotFound(action = NotFoundAction.IGNORE)
    private Mdl_arrival mdl_arrival;
    

}

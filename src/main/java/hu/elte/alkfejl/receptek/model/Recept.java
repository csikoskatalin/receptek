package hu.elte.alkfejl.receptek.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;



import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "RECIPES")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Recept extends BaseEntity {


    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String text;


    @ManyToOne(targetEntity = User.class, cascade = {CascadeType.ALL})
    @JoinColumn
    private User user;

    @ElementCollection
    private List<String> alapanyagok;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    public enum Status {
        PUBLIC, PRIVATE
    }


}
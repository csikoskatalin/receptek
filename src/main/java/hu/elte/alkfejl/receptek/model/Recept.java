package hu.elte.alkfejl.receptek.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;



import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Recept {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Version
    private int version;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String text;


    @ManyToOne
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
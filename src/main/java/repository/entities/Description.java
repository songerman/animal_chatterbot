package repository.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "descriptions")
@Getter
@Setter
public class Description {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private String value;

    @JsonBackReference
    @ManyToMany(mappedBy = "descriptions")
    private List<Animal> animals;

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}

package company.ggi.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by etudiant on 16/04/17.
 */

@Entity
@Table(name = "GAME")
public class Game implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String Name;
    private String discription;
    @OneToOne
    @JoinColumn(name="id_category")
    private Category category;

    public Game() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Game(String name, String discription, Category category) {

        Name = name;
        this.discription = discription;
        this.category = category;
    }

}

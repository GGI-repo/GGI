package company.ggi.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Ismail ELFAQIR on 10/04/2017.
 */
@Entity
@Table(name = "CATEGORY")
public class Category  implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(unique=true)
    private String name;

    public Category() {
        this.name = null;
    }

    public Category(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

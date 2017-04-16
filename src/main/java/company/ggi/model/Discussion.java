package company.ggi.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by etudiant on 10/04/17.
 */
@Entity
@Table(name = "DISCUSSION")
public class Discussion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    public Discussion(String title) {
        this.title = title;
    }

    public Discussion() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}

package company.ggi.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by etudiant on 15/04/17.
 */
@Entity
@Table(name = "USER_GROUP")
public class UserGroup {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Date creation_date;
    private Double credit;

    public UserGroup() {
    }

    public UserGroup(String name, Date creation_date, Double credit) {
        this.name = name;
        this.creation_date = creation_date;
        this.credit = credit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }
}

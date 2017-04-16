package company.ggi.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by etudiant on 15/04/17.
 */
@Entity
@Table(name = "USER_GROUP")
public class UserGroup implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_userGroup")
    private Integer id;

    @Column(unique = true)
    private String name;

    @Column(nullable = false)
    private Date creationDate;

    private Double credit;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userGroup")
    private List<User> comments = new ArrayList<User>();

    public UserGroup() {
    }

    public UserGroup(String name, Date creation_date, Double credit) {
        this.name = name;
        this.creationDate = creation_date;
        this.credit = credit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }
}

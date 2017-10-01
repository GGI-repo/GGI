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
public class UserGroup implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_userGroup")
    private Integer id;

    @Column(unique = true)
    private String name;

    @Column(nullable = false)
    private Date creationDate;

    private Double credit;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "group_details",
            joinColumns = {@JoinColumn(name = "id_users")},
            inverseJoinColumns = {@JoinColumn(name = "id_userGroup")})
    private List<User> userList = new ArrayList<>();

    public UserGroup() {
    }

    public UserGroup(String name, Date creation_date, Double credit) {
        this.name = name;
        this.creationDate = creation_date;
        this.credit = credit;
    }

    public UserGroup(String name, Date creation_date, Double credit, List<User> userList) {
        this.name = name;
        this.creationDate = creation_date;
        this.credit = credit;
        this.userList = userList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
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

package company.ggi.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Adam on 16/04/2017.
 */

@Entity
@Table(name = "BET")
public class Bet implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private int credit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_party", nullable = false)
    private Party party;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_user")
    private User user;

    public Bet() { }

    public Bet(int credit, Party party, User user) {
        this.credit = credit;
        this.party = party;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

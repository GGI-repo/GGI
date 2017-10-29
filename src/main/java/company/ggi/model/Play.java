package company.ggi.model;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Benmoumen on 01/10/2017.
 */
@Entity
@Table(name = "PLAY")
public class Play implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private DateTime startingTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_party", nullable = false)
    private Party party;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_userGroup", nullable = false)
    private UserGroup userGroup;

    public Play(DateTime startingTime, Party party, UserGroup userGroup) {
        this.startingTime = startingTime;
        this.party = party;
        this.userGroup = userGroup;
    }

    public Integer getId() {
        return id;
    }

    public DateTime getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(DateTime startingTime) {
        this.startingTime = startingTime;
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }

}
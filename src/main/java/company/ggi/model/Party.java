package company.ggi.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by etudiant on 16/04/17.
 */
@Entity
@Table(name = "PARTY")
public class Party {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private Date startingDate;
    private String GroupName;
    @OneToOne
    @JoinColumn(name="id_game")
    private Game game;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String groupName) {
        GroupName = groupName;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Party(String name, String description, Date startingDate, String groupName, Game game) {

        this.name = name;
        this.description = description;
        this.startingDate = startingDate;
        GroupName = groupName;
        this.game = game;
    }
}

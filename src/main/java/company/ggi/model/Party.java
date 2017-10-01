package company.ggi.model;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by etudiant on 16/04/17.
 */
@Entity
@Table(name = "PARTY")
public class Party implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private DateTime startingDate;
    @Column(nullable = false)
    private String GroupName;

    @OneToOne
    @JoinColumn(name = "id_game")
    private Game game;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "party")
    private List<Comment> comments = new ArrayList<Comment>();
    ;

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

    public DateTime getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(DateTime startingDate) {
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Party(String name, String description, DateTime startingDate, String groupName, Game game) {

        this.name = name;
        this.description = description;
        this.startingDate = startingDate;
        GroupName = groupName;
        this.game = game;
    }
}

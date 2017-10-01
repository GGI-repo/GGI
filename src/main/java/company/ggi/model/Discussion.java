package company.ggi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by etudiant on 10/04/17.
 */
@Entity
@Table(name = "DISCUSSION")
public class Discussion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private DateTime creationDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "discussion")
    @JsonManagedReference
    private List<DiscussionGroup> discussionGroups = new ArrayList<>();

    public Discussion(String title) {
        this.title = title;
        this.creationDate = new DateTime();
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

    public DateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(DateTime creationDate) {
        this.creationDate = creationDate;
    }

    public List<DiscussionGroup> getDiscussionGroups() {
        return discussionGroups;
    }

    public void setDiscussionGroups(List<DiscussionGroup> discussionGroups) {
        this.discussionGroups = discussionGroups;
    }
}

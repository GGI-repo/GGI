package company.ggi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
    private Date creationDate = new Date();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "discussion")
    @JsonManagedReference
    private List<DiscussionGroup> discussionGroups = new ArrayList<>();

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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<DiscussionGroup> getDiscussionGroups() {
        return discussionGroups;
    }

    public void setDiscussionGroups(List<DiscussionGroup> discussionGroups) {
        this.discussionGroups = discussionGroups;
    }
}

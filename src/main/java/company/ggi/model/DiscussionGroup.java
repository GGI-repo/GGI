package company.ggi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by ismail ELFAQIR on 15/04/2017.
 */
@Entity
@Table(name = "DISCUSSIONGROUP")
public class DiscussionGroup implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_user")
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_discussion")
    private Discussion discussion;

    @Column(nullable = false)
    private ROLE role;

    @Column(nullable = false)
    private Date addedToDiscussion = new Date();

    @Column(nullable = true)
    private Date removedFromDiscussion = null;

    public static enum ROLE {
        Owner, Nothing
    }

    public DiscussionGroup(User user, Discussion discussion, ROLE role) {
        this.user = user;
        this.discussion = discussion;
        this.role = role;
    }

    public DiscussionGroup() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Discussion getDiscussion() {
        return discussion;
    }

    public void setDiscussion(Discussion discussion) {
        this.discussion = discussion;
    }

    public ROLE getRole() {
        return role;
    }

    public void setRole(ROLE role) {
        this.role = role;
    }

    public Date getAddedToDiscussion() {
        return addedToDiscussion;
    }

    public void setAddedToDiscussion(Date addedToDiscussion) {
        this.addedToDiscussion = addedToDiscussion;
    }

    public Date getRemovedFromDiscussion() {
        return removedFromDiscussion;
    }

    public void setRemovedFromDiscussion(Date removedFromDiscussion) {
        this.removedFromDiscussion = removedFromDiscussion;
    }
}

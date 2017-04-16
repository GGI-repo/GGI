package company.ggi.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ismail ELFAQIR on 15/04/2017.
 */
@Entity
@Table(name = "DISCUSSIONGROUP")
public class DiscussionGroup implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name="id_discussion")
    private Discussion discussion;

    private ROLE role;

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
}

package company.ggi.model;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ismail ELFAQIR on 16/04/2017.
 */

@Entity
@Table(name = "MESSAGE")
public class Message implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_discussion_group")
    private DiscussionGroup discussionGroup;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private DateTime sentDate;

    public Message(DiscussionGroup discussionGroup, String message) {
        this.discussionGroup = discussionGroup;
        sentDate = new DateTime();
        this.message = message;
    }

    public Message() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DiscussionGroup getDiscussionGroup() {
        return discussionGroup;
    }

    public void setDiscussionGroup(DiscussionGroup discussionGroup) {
        this.discussionGroup = discussionGroup;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DateTime getSentDate() {
        return sentDate;
    }

    public void setSentDate(DateTime sentDate) {
        this.sentDate = sentDate;
    }
}

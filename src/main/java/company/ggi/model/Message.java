package company.ggi.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ismail ELFAQIR on 16/04/2017.
 */

@Entity
@Table(name = "MESSAGE")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_discussion_group")
    private DiscussionGroup discussionGroup;

    private String message;

    private Date sentDate;

    public Message(DiscussionGroup discussionGroup, String message, Date sentDate) {
        this.discussionGroup = discussionGroup;
        this.message = message;
        this.sentDate = sentDate;
    }

    public Message(DiscussionGroup discussionGroup, String message) {
        this.discussionGroup = discussionGroup;
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

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }
}

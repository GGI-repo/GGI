package company.ggi.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Adam on 16/04/2017.
 */
@Entity
@Table(name = "COMMENT")
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition ="text", unique=true , nullable = false)
    private String content;

    @Column(nullable = false)
    private Date commentDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_party", nullable = false)
    private Party party;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_user")
    private User user;

    public Comment(String content, Date commentDate) {
        this.content = content;
        this.commentDate = commentDate;
    }

    public Comment(String content, Date commentDate, Party party, User user) {
        this.content = content;
        this.commentDate = commentDate;
        this.party = party;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }
}

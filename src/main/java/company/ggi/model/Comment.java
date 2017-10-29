package company.ggi.model;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Adam BENJBARA on 16/04/2017.
 */
@Entity
@Table(name = "COMMENT")
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition ="text", unique=true , nullable = false)
    private String content;

    @Column(nullable = false)
    private DateTime commentDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_party", nullable = false)
    private Party party;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;

    public Comment(){
    }

    public Comment(String content, Party party, User user) {
        this.content = content;
        this.commentDate = new DateTime();
        this.party = party;
        this.user = user;
    }

    public Comment(String contenu, DateTime commentDate) {
        this.content = contenu;
        this.commentDate = commentDate;
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


    public DateTime getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(DateTime commentDate) {
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

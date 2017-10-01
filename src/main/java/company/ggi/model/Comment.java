package company.ggi.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Adam on 16/04/2017.
 */
@Entity
@Table(name = "COMMENT")
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "text", unique = true, nullable = false)
    private String contenu;

    @Column(nullable = false)
    private String commentDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_party", nullable = false)
    private Party party;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;

    public Comment(String contenu, String commentDate) {
        this.contenu = contenu;
        this.commentDate = commentDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
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

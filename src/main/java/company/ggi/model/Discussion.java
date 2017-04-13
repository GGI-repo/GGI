package company.ggi.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by etudiant on 10/04/17.
 */
@Entity
@Table(name = "DISCUSSION")
public class Discussion {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    public Discussion(String message, Date sendDate, User sender) {
        this.message = message;
        this.sendDate = sendDate;
        this.sender = sender;
    }

    public Discussion(){}

    public String getMessage() {
        return message;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public User getSender() {
        return sender;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    private String message;
    private Date sendDate;

    @OneToOne
    @JoinColumn(name="id_user")
    private User sender;

}

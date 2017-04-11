package company.ggi.model;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Driss BENMOUMEN on 10/04/17.
 */
@Entity
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String lastName;

    public User(String lastName, String userName, String firstName, String email, String birthDay) {
        this.lastName = lastName;
        this.userName = userName;
        this.firstName = firstName;
        this.email = email;
        this.birthDay = birthDay;
        DateFormat dateFormat = new SimpleDateFormat("MM/DD/YYYY HH:mm:ss");
        this.registration = new Date();
        dateFormat.format(this.registration);
        this.credit = 5.0;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public Date getRegistration() {
        return registration;
    }

    public Double getCredit() {
        return credit;
    }

    private String userName;
    private String firstName;
    private String email;
    private String birthDay;
    private Date registration;
    private Double credit;


}

package company.ggi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.joda.time.DateTime;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Driss BENMOUMEN & Ismail ELFAQIR on 10/04/17.
 */
@Entity
@Table(name = "USERS")
public class User implements Serializable, UserDetails {
    public static enum ROLE {
        ADMIN, USER
    };

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_users")
    private Integer id;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true)
    private String userName;

    private String password;

    @Column(nullable = false)
    private String firstName;

    @Column(unique = true)
    private String email;

    private DateTime birthDay;

    private DateTime registration;

    private Double credit;

    private Boolean enabled = true;

    private ROLE role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "userList")
    private List<UserGroup> userGroups = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @JsonManagedReference
    private List<DiscussionGroup> discussionGroups = new ArrayList<>();

    public User() {
    }

    public User(String lastName, String userName, String firstName, String email, DateTime birthDay) {
        this.lastName = lastName;
        this.userName = userName;
        this.firstName = firstName;
        this.email = email;
        this.birthDay = birthDay;
        this.registration = new DateTime();
        this.credit = 5.0;
    }

    public User(String lastName, String userName, String firstName, String email, DateTime birthDay, List<UserGroup> userGroups) {
        this.lastName = lastName;
        this.userName = userName;
        this.firstName = firstName;
        this.email = email;
        this.birthDay = birthDay;
        this.registration = new DateTime();
        this.credit = 5.0;
        this.userGroups = userGroups;
    }

    public List<UserGroup> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(List<UserGroup> userGroups) {
        this.userGroups = userGroups;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirthDay(DateTime birthDay) {
        this.birthDay = birthDay;
    }

    public void setRegistration(DateTime registration) {
        this.registration = registration;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public Integer getId() {
        return id;
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

    public DateTime getBirthDay() {
        return birthDay;
    }

    public DateTime getRegistration() {
        return registration;
    }

    public Double getCredit() {
        return credit;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<DiscussionGroup> getDiscussionGroups() {
        return discussionGroups;
    }

    public void setDiscussionGroups(List<DiscussionGroup> discussionGroups) {
        this.discussionGroups = discussionGroups;
    }

    public ROLE getRole() {
        return role;
    }

    public void setRole(ROLE role) {
        this.role = role;
    }

    /************* User Details methods ***********/
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // we never lock accounts
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // credentials never expire
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }
}

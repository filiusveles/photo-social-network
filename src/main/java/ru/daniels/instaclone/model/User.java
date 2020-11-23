package ru.daniels.instaclone.model;


import javax.persistence.*;

@Entity
@Table(name="users", schema = "data")
public class User {

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String nickname;

/*    private Subscribers subscribers;*/

    @Id
    @SequenceGenerator(name="users_seq", sequenceName = "users_id_seq", allocationSize = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @Column(name = "id")
    public long getId(){
        return id;
    }

    @Column(name = "firstname")
    public String getFirstName() {
        return firstName;
    }

    @Column(name = "lastname")
    public String getLastName() {
        return lastName;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    @Column(name = "nickname")
    public String getNickname() {
        return nickname;
    }

/*    public Subscribers getSubscribers() {
        return subscribers;
    }*/

    public void setId(long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}

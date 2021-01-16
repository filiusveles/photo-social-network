package ru.daniels.instaclone.api.security;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.daniels.instaclone.api.model.dbentity.DBEntity;
import ru.daniels.instaclone.api.model.dbentity.Image;
import ru.daniels.instaclone.api.model.dbentity.Post;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name="users", schema = "data")
@NoArgsConstructor
@EqualsAndHashCode(of = {"email"})
@ToString(of={"id", "nickname"})
public class SecUser implements DBEntity, UserDetails {
    @Id
    @SequenceGenerator(name="user_seq", sequenceName = "data.users_id_seq", allocationSize = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @Column(name = "id")
    @Getter
    private long id;

    @Column(name = "firstname")
    @Getter @Setter
    private String firstName;

    @Column(name = "lastname")
    @Getter @Setter
    private String lastName;

    @Column(name = "email")
    @Getter
    private String email;

    @Column(name = "password")
    @Getter @Setter
    private String password;

    @Column(name = "phone")
    @Getter @Setter
    private String phone;

    @Column(name = "nickname")
    @Getter @Setter
    private String nickname;

    @OneToOne
    @JoinColumn(name = "avatar")
    @Getter @Setter
    private Image avatar;

    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    @Getter @Setter
    private Collection<Post> posts;

    @Transient
    @Getter @Setter
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toSet());
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
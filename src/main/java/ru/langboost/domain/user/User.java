/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.langboost.domain.user;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.langboost.domain.AbstractEntity;

/**
 *
 * @author Ильдар
 */
@Entity
@Table(name = "users")
@NamedQueries({
    @NamedQuery(name = "User.findAll",
            query = "Select c from User c"),
    @NamedQuery(name = "User.findByLogin",
            query = "Select c from User c where c.credential.login = :login")
})
public class User extends AbstractEntity<User> implements UserDetails {

    public static final User NULL = new User(
            new UserCredential("NULL", "NULL"),
            new UserData("NULL", "NULL", "NULL",null));
    
    @Embedded
    private UserCredential credential;
    
    @Embedded
    private UserData userData;
    
    @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE)
    private Set<UserRole> roles = new HashSet<>();

    public User() {
    }
    
    public User(UserCredential credential,UserData data) {
        this.credential = credential;
        this.userData = data;
    }

    public UserCredential getCredential() {
        return credential;
    }

    public void setCredential(UserCredential credential) {
        this.credential = credential;
    }

    public boolean isNull() {
        return this == NULL;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (roles == null) {
            return Collections.emptyList();
        }
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (UserRole userRole : roles) {
            authorities.add(new SimpleGrantedAuthority(userRole.getRole().getName()));
        }
        return authorities;
    }
    
    @Override
    public String getUsername() {
        return credential.getLogin();
    }
    
    @Override
    public String getPassword() {
        return credential.getEncodedPassword();
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
    
     public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    @Override
    public void merge(User entity) {
        this.userData = entity.getUserData();
        this.credential = entity.getCredential();
    }
    
}

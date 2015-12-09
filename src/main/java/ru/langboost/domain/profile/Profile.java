package ru.langboost.domain.profile;

import ru.langboost.domain.AbstractEntity;
import ru.langboost.domain.user.User;

import javax.persistence.*;

/**
 * Created by Ildar Gafarov on 09.12.15.
 */
@Entity
@Table(name = "profiles")
@NamedQueries({
        @NamedQuery(name = "Profile.findByUser",
                query = "Select c from Profile c where c.user = :user")
})
public class Profile extends AbstractEntity<Profile> {

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Profile() {
    }

    public Profile(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void merge(Profile entity) {

    }
}

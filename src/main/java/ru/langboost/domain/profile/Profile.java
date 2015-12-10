package ru.langboost.domain.profile;

import ru.langboost.domain.AbstractEntity;
import ru.langboost.domain.user.User;
import ru.langboost.domain.Dictionary.Dictionary;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "profile", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Dictionary> dictionaries = new ArrayList<>();

    public Profile() {
    }

    public Profile(User user) {
        this.user = user;
    }

    public List<Dictionary> getDictionaries() {
        return dictionaries;
    }

    public void setDictionaries(List<Dictionary> dictionaries) {
        this.dictionaries = dictionaries;
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

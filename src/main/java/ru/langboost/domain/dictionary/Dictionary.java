package ru.langboost.domain.dictionary;

import ru.langboost.domain.AbstractEntity;
import ru.langboost.domain.profile.Profile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ildar Gafarov on 09.12.15.
 */
@Entity
@Table(name = "dictionaries")
@NamedQueries({
        @NamedQuery(name = "Dictionary.findAll",
                query = "Select c from Dictionary c"),
        @NamedQuery(name = "Dictionary.findByProfileAndDefault",
                query = "Select c from Dictionary c where c.defaulted = :defaulted and c.profile = :profile"),
        @NamedQuery(name = "Dictionary.findByProfile",
                query = "Select c from Dictionary c where c.profile = :profile"),
})
public class Dictionary extends AbstractEntity<Dictionary> {

    @OneToMany(mappedBy = "dictionary", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Word> words = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Column(name = "title")
    private String title;

    @Column(name = "is_default")
    private boolean defaulted;

    public Dictionary(String title, Profile profile) {
        this.title = title;
        this.profile = profile;
    }

    public Dictionary() {

    }

    public boolean isDefaulted() {
        return defaulted;
    }

    public void setDefaulted(boolean defaulted) {
        this.defaulted = defaulted;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void merge(Dictionary entity) {

    }
}

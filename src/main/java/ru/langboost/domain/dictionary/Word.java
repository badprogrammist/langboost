package ru.langboost.domain.dictionary;

import ru.langboost.domain.AbstractEntity;

import javax.persistence.*;

/**
 * Created by Ildar Gafarov on 09.12.15.
 */
@Entity
@Table(name = "words")
@NamedQueries({
        @NamedQuery(name = "Word.findAll",
                query = "Select c from Word c"),
        @NamedQuery(name = "Word.findByDictionary",
                query = "Select c from Word c where c.dictionary = :dictionary")
})
public class Word extends AbstractEntity<Word> {

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "dictionary_id")
    private Dictionary dictionary;


    public Word(String content, Dictionary dictionary) {
        this.content = content;
        this.dictionary = dictionary;
    }

    public Word() {

    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public void setDictionary(Dictionary set) {
        this.dictionary = set;
    }

    @Override
    public void merge(Word entity) {
        this.content = entity.content;
    }
}

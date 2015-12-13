package ru.langboost.domain.rule;

import ru.langboost.domain.AbstractEntity;

import javax.persistence.*;

/**
 * Created by Ildar Gafarov on 12.12.15.
 */
@Entity
@Table(name = "rules")
@NamedQueries({
        @NamedQuery(name = "Rule.findAll",
                query = "Select c from Rule c")
})
public class Rule extends AbstractEntity<Rule> {

    @Column(name = "title")
    private String title;

    @Column(name = "content", columnDefinition = "text")
    private String content;

    public Rule(String title, String content) {
        this.content = content;
        this.title = title;
    }

    public Rule() {
    }

    @Override
    public void merge(Rule entity) {
        this.title = entity.getTitle();
        this.content = entity.getContent();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

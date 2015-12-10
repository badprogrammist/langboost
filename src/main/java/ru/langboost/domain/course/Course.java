package ru.langboost.domain.course;

import ru.langboost.domain.AbstractEntity;
import ru.langboost.domain.dictionary.Word;
import ru.langboost.domain.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ildar Gafarov on 10.12.15.
 */
@Entity
@Table(name = "courses")
@NamedQueries({
        @NamedQuery(name = "Course.findAll",
                query = "Select c from Course c"),
        @NamedQuery(name = "Course.findByAuthor",
                query = "Select c from Course c where c.author = :author")
})
public class Course extends AbstractEntity<Course> {

    @Column(name = "title")
    private String title;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @OneToMany(mappedBy = "course", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Unit> units = new ArrayList<>();

    public Course(String title, String description, User author) {
        this.title = title;
        this.author = author;
        this.description = description;
    }

    public Course() {
    }

    public boolean isOwner(User user) {
        if(user != null) {
            return user.equals(author);
        }
        return false;
    }

    @Override
    public void merge(Course entity) {
        this.title = entity.getTitle();
        this.description = entity.getDescription();
    }

    public List<Unit> getUnits() {
        return units;
    }

    public void setUnits(List<Unit> units) {
        this.units = units;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}

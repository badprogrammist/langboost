package ru.langboost.domain.course;

import ru.langboost.domain.AbstractEntity;
import ru.langboost.domain.user.User;

import javax.persistence.*;

/**
 * Created by Ildar Gafarov on 10.12.15.
 */
@Entity
@Table(name = "units")
@NamedQueries({
        @NamedQuery(name = "Unit.findAll",
                query = "Select c from Unit c"),
        @NamedQuery(name = "Unit.findByCourse",
                query = "Select c from Unit c where c.course = :course order by c.orderNumber asc ")
})
public class Unit extends AbstractEntity<Unit> {

    @Column(name = "title")
    private String title;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "order_number")
    private int orderNumber;

    public Unit(String title, String description, Course course) {
        this.title = title;
        this.course = course;
        this.description = description;
    }

    public Unit() {

    }

    @Override
    public void merge(Unit entity) {
        this.title = entity.getTitle();
        this.description = entity.getDescription();
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}

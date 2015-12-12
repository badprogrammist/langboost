package ru.langboost.domain.profile;

import ru.langboost.domain.AbstractEntity;
import ru.langboost.domain.course.Course;

import javax.persistence.*;

/**
 * Created by Ildar Gafarov on 12.12.15.
 */
@Entity
@Table(name = "profile_courses")
@NamedQueries({
        @NamedQuery(name = "ProfileCourse.findByProfile",
                query = "Select c from ProfileCourse c where c.profile = :profile"),
        @NamedQuery(name = "ProfileCourse.findByCourse",
                query = "Select c from ProfileCourse c where c.course = :course"),
        @NamedQuery(name = "ProfileCourse.findByCourseAndProfile",
                query = "Select c from ProfileCourse c where c.course = :course and c.profile = :profile")
})
public class ProfileCourse extends AbstractEntity<ProfileCourse> {

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public ProfileCourse(Profile profile, Course course) {
        this.profile = profile;
        this.course = course;
    }

    public ProfileCourse() {
    }

    @Override
    public void merge(ProfileCourse entity) {

    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}

package ru.langboost.domain.profile;

import ru.langboost.domain.EntityRepository;
import ru.langboost.domain.course.Course;

import java.util.List;

/**
 * Created by Ildar Gafarov on 12.12.15.
 */
public interface ProfileCourseRepository extends EntityRepository<ProfileCourse> {

    List<ProfileCourse> findByProfile(Profile profile);
    List<ProfileCourse> findByCourse(Course course);
    ProfileCourse findByCourseAndProfile(Course course, Profile profile);

}

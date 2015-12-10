package ru.langboost.domain.course;

import ru.langboost.domain.EntityRepository;
import ru.langboost.domain.user.User;

import java.util.List;

/**
 * Created by Ildar Gafarov on 09.12.15.
 */
public interface CourseRepository extends EntityRepository<Course> {

    List<Course> findByAuthor(User author);

}

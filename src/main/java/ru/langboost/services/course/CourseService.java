package ru.langboost.services.course;

import org.springframework.transaction.annotation.Transactional;
import ru.langboost.domain.course.Course;
import ru.langboost.domain.course.Unit;
import ru.langboost.domain.user.User;
import ru.langboost.services.ServiceException;

import java.util.List;

/**
 * Created by Ildar Gafarov on 10.12.15.
 */
public interface CourseService {

    Course createCourse(String title, String description, User author) throws ServiceException;
    void updateCourse(String title, String description, Long id, User user) throws ServiceException;
    Unit createUnit(String title, String description, Course course) throws ServiceException;
    List<Unit> getUnits(Course course) throws ServiceException;
    List<Course> getCourses(User author) throws ServiceException;
    Course getCourse(Long id);
    Unit getUnit(Long id);


}

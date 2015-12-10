package ru.langboost.services.course;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.langboost.domain.course.Course;
import ru.langboost.domain.course.CourseRepository;
import ru.langboost.domain.course.Unit;
import ru.langboost.domain.course.UnitRepository;
import ru.langboost.domain.user.User;
import ru.langboost.services.ServiceException;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Ildar Gafarov on 10.12.15.
 */
@Service
public class DefaultCourseService implements CourseService {

    @Inject
    private CourseRepository courseRepository;

    @Inject
    private UnitRepository unitRepository;


    @Transactional
    @Override
    public Course createCourse(String title, String description, User author) throws ServiceException {
        if(author == null) {
            throw new ServiceException("Не указан автор!");
        }
        if(title == null) {
            throw new ServiceException("Не указано название курса!");
        }
        Course course = new Course(title,description,author);
        courseRepository.store(course);
        return course;
    }

    @Override
    @Transactional
    public void updateCourse(String title, String description, Long id, User author) throws ServiceException {
        Course old = getCourse(id);
        if(old == null) {
            throw new ServiceException("Такой курс не найден");
        }
        if(author == null) {
            throw new ServiceException("Не указан автор!");
        }
        if(title == null) {
            throw new ServiceException("Не указано название курса!");
        }
        if(!old.isOwner(author)) {
            throw new ServiceException("У Вас нет прав на это действие");
        }
        old.setTitle(title);
        old.setDescription(description);
        courseRepository.update(old);
    }

    @Transactional
    @Override
    public Unit createUnit(String title, String description, Course course) throws ServiceException {
        if(course == null) {
            throw new ServiceException("Не указан курс!");
        }
        if(title == null) {
            throw new ServiceException("Не указано название юнита!");
        }
        Unit unit = new Unit(title,description,course);
        unitRepository.store(unit);
        return unit;
    }

    @Override
    public List<Unit> getUnits(Course course) throws ServiceException {
        if(course == null) {
            throw new ServiceException("Не указан курс!");
        }
        return unitRepository.findByCourse(course);
    }

    @Override
    public List<Course> getCourses(User author) throws ServiceException {
        if(author == null) {
            throw new ServiceException("Не указан автор!");
        }
        return courseRepository.findByAuthor(author);
    }

    @Override
    public Course getCourse(Long id) {
        return courseRepository.get(id);
    }

    @Override
    public Unit getUnit(Long id) {
        return unitRepository.get(id);
    }

}

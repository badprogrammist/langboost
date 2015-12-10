package ru.langboost.repositories.course;

import org.springframework.stereotype.Repository;
import ru.langboost.domain.course.Course;
import ru.langboost.domain.course.CourseRepository;
import ru.langboost.domain.dictionary.Dictionary;
import ru.langboost.domain.profile.Profile;
import ru.langboost.domain.user.User;
import ru.langboost.repositories.AbstractRepositoryJPA;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

/**
 * Created by Ildar Gafarov on 09.12.15.
 */
@Repository
public class CourseRepositoryJPA extends AbstractRepositoryJPA<Course> implements CourseRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public CourseRepositoryJPA() {
        super(Course.class);
    }

    @Override
    public List<Course> findByAuthor(User author) {
        try {
            return entityManager.createNamedQuery("Course.findByAuthor", Course.class)
                    .setParameter("author", author)
                    .getResultList();

        } catch (Exception ex) {
            return Collections.emptyList();
        }
    }



    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

}

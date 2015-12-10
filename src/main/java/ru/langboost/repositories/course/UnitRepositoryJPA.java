package ru.langboost.repositories.course;

import org.springframework.stereotype.Repository;
import ru.langboost.domain.course.Course;
import ru.langboost.domain.course.CourseRepository;
import ru.langboost.domain.course.Unit;
import ru.langboost.domain.course.UnitRepository;
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
public class UnitRepositoryJPA extends AbstractRepositoryJPA<Unit> implements UnitRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public UnitRepositoryJPA() {
        super(Unit.class);
    }

    @Override
    public List<Unit> findByCourse(Course course) {
        try {
            return entityManager.createNamedQuery("Unit.findByCourse", Unit.class)
                    .setParameter("course", course)
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

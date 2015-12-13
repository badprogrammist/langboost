package ru.langboost.repositories.course;

import org.springframework.stereotype.Repository;
import ru.langboost.domain.course.Unit;
import ru.langboost.domain.course.exercise.TextMatchingExercise;
import ru.langboost.domain.course.exercise.TextMatchingExerciseRepository;
import ru.langboost.repositories.AbstractRepositoryJPA;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

/**
 * Created by Ildar Gafarov on 12.12.15.
 */
@Repository
public class TextMatchingExerciseRepositoryJPA  extends AbstractRepositoryJPA<TextMatchingExercise> implements TextMatchingExerciseRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public TextMatchingExerciseRepositoryJPA() {
        super(TextMatchingExercise.class);
    }

    @Override
    public List<TextMatchingExercise> findByUnit(Unit unit) {
        try {
            return entityManager.createNamedQuery("TextMatchingExercise.findByUnit", TextMatchingExercise.class)
                    .setParameter("unit", unit)
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

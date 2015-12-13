package ru.langboost.repositories.course;

import org.springframework.stereotype.Repository;
import ru.langboost.domain.course.exercise.TextMatchingExercise;
import ru.langboost.domain.course.exercise.TextMatchingExerciseRule;
import ru.langboost.domain.course.exercise.TextMatchingExerciseRuleRepository;
import ru.langboost.domain.rule.Rule;
import ru.langboost.repositories.AbstractRepositoryJPA;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

/**
 * Created by Ildar Gafarov on 09.12.15.
 */
@Repository
public class TextMatchingExerciseRuleRepositoryJPA extends AbstractRepositoryJPA<TextMatchingExerciseRule> implements TextMatchingExerciseRuleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public TextMatchingExerciseRuleRepositoryJPA() {
        super(TextMatchingExerciseRule.class);
    }

    @Override
    public List<TextMatchingExerciseRule> findByExercise(TextMatchingExercise exercise) {
        try {
            return entityManager.createNamedQuery("TextMatchingExerciseRule.findByExercise", TextMatchingExerciseRule.class)
                    .setParameter("exercise", exercise)
                    .getResultList();

        } catch (Exception ex) {
            return Collections.emptyList();
        }
    }

    @Override
    public List<TextMatchingExerciseRule> findByRule(Rule rule) {
        try {
            return entityManager.createNamedQuery("TextMatchingExerciseRule.findByRule", TextMatchingExerciseRule.class)
                    .setParameter("rule", rule)
                    .getResultList();

        } catch (Exception ex) {
            return Collections.emptyList();
        }
    }

    @Override
    public TextMatchingExerciseRule findByRuleAndExercise(Rule rule, TextMatchingExercise exercise) {
        try {
            return entityManager.createNamedQuery("TextMatchingExerciseRule.findByRuleAndExercise", TextMatchingExerciseRule.class)
                    .setParameter("rule", rule)
                    .setParameter("exercise", exercise)
                    .getSingleResult();

        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

}

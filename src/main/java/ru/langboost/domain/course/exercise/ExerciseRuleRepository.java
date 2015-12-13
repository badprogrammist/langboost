package ru.langboost.domain.course.exercise;

import ru.langboost.domain.EntityRepository;
import ru.langboost.domain.rule.Rule;

import java.util.List;

/**
 * Created by Ildar Gafarov on 12.12.15.
 */
public interface ExerciseRuleRepository<ER extends AbstractExerciseRule,E extends AbstractExercise> extends EntityRepository<ER> {

    List<ER> findByExercise(E exercise);
    List<ER> findByRule(Rule rule);
    ER findByRuleAndExercise(Rule rule, E exercise);

}

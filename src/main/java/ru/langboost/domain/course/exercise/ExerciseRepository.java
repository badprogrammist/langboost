package ru.langboost.domain.course.exercise;

import ru.langboost.domain.EntityRepository;
import ru.langboost.domain.course.Unit;

import java.util.List;

/**
 * Created by Ildar Gafarov on 12.12.15.
 */
public interface ExerciseRepository<E extends AbstractExercise> extends EntityRepository<E> {

    List<E> findByUnit(Unit unit);

}

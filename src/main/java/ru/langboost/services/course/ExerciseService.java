package ru.langboost.services.course;

import ru.langboost.domain.course.Unit;
import ru.langboost.domain.course.exercise.AbstractExercise;
import ru.langboost.domain.course.exercise.ExerciseType;
import ru.langboost.services.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * Created by Ildar Gafarov on 12.12.15.
 */
public interface ExerciseService {
    AbstractExercise createExercise(ExerciseType type, Unit unit) throws ServiceException;
    List<AbstractExercise> getExercises(Unit unit);
    AbstractExercise getExercise(Long id, ExerciseType type);
    AbstractExercise updateExercise(AbstractExercise newData,ExerciseType type, Long id) throws ServiceException;
    void updateOrder(Map<Long,Integer> indexedExercises, Unit unit) throws ServiceException;
}

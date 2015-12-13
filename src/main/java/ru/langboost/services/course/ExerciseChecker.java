package ru.langboost.services.course;

import ru.langboost.domain.course.exercise.AbstractExercise;
import ru.langboost.domain.course.exercise.AbstractPerformingResult;
import ru.langboost.domain.course.exercise.ExerciseCheckingResult;
import ru.langboost.services.ServiceException;

/**
 * Created by Ildar Gafarov on 12.12.15.
 */
public interface ExerciseChecker<PR extends AbstractPerformingResult> {

    public ExerciseCheckingResult check(PR performingResult) throws ExerciseCheckingException;

}

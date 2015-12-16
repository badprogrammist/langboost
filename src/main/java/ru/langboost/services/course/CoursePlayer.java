package ru.langboost.services.course;

import ru.langboost.domain.course.Course;
import ru.langboost.domain.course.Unit;
import ru.langboost.domain.course.exercise.AbstractExercise;
import ru.langboost.domain.course.exercise.AbstractPerformingResult;
import ru.langboost.domain.profile.Profile;
import ru.langboost.services.ServiceException;

import java.io.Serializable;

/**
 * Created by Ildar Gafarov on 14.12.15.
 */
public interface CoursePlayer extends Serializable {

    void init(Course course, Profile profile) throws ServiceException;
    void check(AbstractPerformingResult performingResult) throws ExerciseCheckingException;
    boolean hasNextExercise();
    boolean hasNextUnit();
    boolean isInitialized();
    Unit getUnit();
    Course getCourse();
    AbstractExercise getExercise();
    void abort();
    void nextExercise() throws ServiceException;
    void nextUnit() throws ServiceException;
}

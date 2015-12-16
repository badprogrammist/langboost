package ru.langboost.services.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.langboost.domain.course.Course;
import ru.langboost.domain.course.Unit;
import ru.langboost.domain.course.exercise.AbstractExercise;
import ru.langboost.domain.course.exercise.AbstractPerformingResult;
import ru.langboost.domain.course.exercise.ExerciseType;
import ru.langboost.domain.profile.Profile;
import ru.langboost.services.ServiceException;
import ru.langboost.services.profile.ProfileService;

import javax.inject.Inject;
import java.util.Iterator;

/**
 * Created by Ildar Gafarov on 14.12.15.
 */
@Service
@Scope("session")
public class DefaultCoursePlayer implements CoursePlayer {

    private Course course;
    private Profile profile;
    private Iterator<Unit> unitIterator;
    private Iterator<AbstractExercise> exerciseIterator;
    private Unit unit;
    private AbstractExercise exercise;

    @Inject
    private ProfileService profileService;

    @Inject
    private ExerciseService exerciseService;

    @Inject
    @Qualifier("tm")
    private ExerciseChecker tmExerciseChecker;

    @Override
    public void init(Course course, Profile profile) throws ServiceException {
        if(course == null) {
            throw new ServiceException("Не задан курс!");
        }
        if(profile == null) {
            throw new ServiceException("Не задан профиль!");
        }
        if(!profileService.isAttached(profile.getUser(), course)) {
            throw new ServiceException("Курс не прикреплен к профилю!");
        }
        this.course = course;
        this.profile = profile;
        unitIterator = course.getUnits().iterator();
        if(!hasNextUnit()) {
            throw new ServiceException("Нет следующего юнита");
        }
        unit =unitIterator.next();
        exerciseIterator = exerciseService.getExercises(unit).iterator();
        if(!hasNextExercise()) {
            throw new ServiceException("Нет следующего упражнения");
        }
        exercise = exerciseIterator.next();
    }

    @Override
    public void abort() {
        this.course = null;
        this.profile = null;
    }

    @Override
    public void nextExercise() throws ServiceException {
        if(!isInitialized()) {
            throw new ServiceException("Плеер не инициализирован");
        }
        if(!hasNextExercise()) {
            throw new ServiceException("Нет следующего упражнения");
        }
        exercise = exerciseIterator.next();
    }

    @Override
    public void nextUnit() throws ServiceException {
        if(!isInitialized()) {
            throw new ServiceException("Плеер не инициализирован");
        }
        if(!hasNextUnit()) {
            throw new ServiceException("Нет следующего юнита");
        }
        unit =unitIterator.next();
        exerciseIterator = exerciseService.getExercises(unit).iterator();
    }

    @Override
    public void check(AbstractPerformingResult performingResult) throws ExerciseCheckingException {
        if(performingResult == null) {
            throw new ExerciseCheckingException("Нет результатов исполнения");
        }
        boolean checked = false;
        if(performingResult.getExercise().getType() == ExerciseType.TEXT_MATCHING) {
            tmExerciseChecker.check(performingResult);
            checked = true;
        }
        if(!checked) {
            throw new ExerciseCheckingException("Не удалось проверить результат исполнения. Тип не поддерживается");
        }
    }

    @Override
    public boolean hasNextExercise() {
        if(exerciseIterator != null) {
            return exerciseIterator.hasNext();
        }
        return false;
    }

    @Override
    public boolean hasNextUnit() {
        if(unitIterator != null) {
            return unitIterator.hasNext();
        }
        return false;
    }

    @Override
    public Unit getUnit() {
        return unit;
    }

    @Override
    public AbstractExercise getExercise() {
        return exercise;
    }

    @Override
    public boolean isInitialized() {
        return course != null
                && profile != null
                && unitIterator != null
                && exerciseIterator != null;
    }

    @Override
    public Course getCourse() {
        return course;
    }
}

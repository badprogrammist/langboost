package ru.langboost.domain.course.exercise;

/**
 * Created by Ildar Gafarov on 12.12.15.
 */
public abstract class AbstractPerformingResult<E extends AbstractExercise> {

    private E exercise;

    public AbstractPerformingResult(E exercise) {
        this.exercise = exercise;
    }

    public E getExercise() {
        return exercise;
    }
}

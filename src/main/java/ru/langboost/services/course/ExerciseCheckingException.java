package ru.langboost.services.course;

/**
 * Created by Ildar Gafarov on 12.12.15.
 */
public class ExerciseCheckingException extends Exception {

    public ExerciseCheckingException() {
    }

    public ExerciseCheckingException(String message) {
        super(message);
    }

    public ExerciseCheckingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExerciseCheckingException(Throwable cause) {
        super(cause);
    }

    public ExerciseCheckingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

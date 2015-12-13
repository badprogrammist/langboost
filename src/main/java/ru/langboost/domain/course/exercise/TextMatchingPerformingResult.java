package ru.langboost.domain.course.exercise;

/**
 * Created by Ildar Gafarov on 12.12.15.
 */
public class TextMatchingPerformingResult extends AbstractPerformingResult<TextMatchingExercise> {

    private String performingText;

    public TextMatchingPerformingResult(TextMatchingExercise exercise) {
        super(exercise);
    }

    public String getPerformingText() {
        return performingText;
    }

    public void setPerformingText(String performingText) {
        this.performingText = performingText;
    }
}

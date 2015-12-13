package ru.langboost.domain.course.exercise;

import ru.langboost.domain.course.Unit;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ildar Gafarov on 12.12.15.
 */
@Entity
@Table(name = "text_matching_exercises")
@NamedQueries({
        @NamedQuery(name = "TextMatchingExercise.findAll",
                query = "Select c from TextMatchingExercise c"),
        @NamedQuery(name = "TextMatchingExercise.findByUnit",
                query = "Select c from TextMatchingExercise c where c.unit = :unit")
})
public class TextMatchingExercise extends AbstractExercise<TextMatchingExercise,TextMatchingPerformingResult,TextMatchingExerciseRule> {

    @Column(name = "target", length = 512)
    private String target;

    @Column(name = "translate", length = 512)
    private String translate;

    @OneToMany(mappedBy = "exercise",cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<TextMatchingExerciseRule> rules = new ArrayList<>();

    public TextMatchingExercise(Unit unit, String target, String translate) {
        super(unit);
        this.target = target;
        this.translate = translate;
    }

    public TextMatchingExercise() {
    }

    @Override
    public TextMatchingPerformingResult createEmptyPerformingResult() {
        return new TextMatchingPerformingResult(this);
    }

    @Override
    public ExerciseType getType() {
        return ExerciseType.TEXT_MATCHING;
    }

    @Override
    public void merge(TextMatchingExercise entity) {
        this.target = entity.getTarget();
        this.translate = entity.getTranslate();
    }

    @Override
    public List<TextMatchingExerciseRule> getRules() {
        return rules;
    }

    @Override
    public void setRules(List<TextMatchingExerciseRule> rules) {
        this.rules = rules;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }
}

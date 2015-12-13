package ru.langboost.domain.course.exercise;

import ru.langboost.domain.AbstractEntity;
import ru.langboost.domain.rule.Rule;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

/**
 * Created by Ildar Gafarov on 12.12.15.
 */
@MappedSuperclass
public abstract class AbstractExerciseRule<AE extends AbstractExercise, ERU extends AbstractExerciseRule> extends AbstractEntity<ERU> {

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private AE exercise;

    @ManyToOne
    @JoinColumn(name = "rule_id")
    private Rule rule;

    public AbstractExerciseRule(AE exercise, Rule rule) {
        this.exercise = exercise;
        this.rule = rule;
    }

    public AbstractExerciseRule() {

    }

    public AE getExercise() {
        return exercise;
    }

    public void setExercise(AE exercise) {
        this.exercise = exercise;
    }

    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }
}

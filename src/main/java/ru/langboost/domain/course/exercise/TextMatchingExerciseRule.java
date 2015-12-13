package ru.langboost.domain.course.exercise;

import ru.langboost.domain.rule.Rule;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Created by Ildar Gafarov on 12.12.15.
 */
@Entity
@Table(name = "text_matching_exercise_rules")
@NamedQueries({
        @NamedQuery(name = "TextMatchingExerciseRule.findAll",
                query = "Select c from TextMatchingExerciseRule c"),
        @NamedQuery(name = "TextMatchingExerciseRule.findByExercise",
                query = "Select c from TextMatchingExerciseRule c where c.exercise = :exercise"),
        @NamedQuery(name = "TextMatchingExerciseRule.findByRule",
                query = "Select c from TextMatchingExerciseRule c where c.rule = :rule"),
        @NamedQuery(name = "TextMatchingExerciseRule.findByRuleAndExercise",
                query = "Select c from TextMatchingExerciseRule c where c.rule = :rule and c.exercise = :exercise")
})
public class TextMatchingExerciseRule extends AbstractExerciseRule<TextMatchingExercise,TextMatchingExerciseRule> {

    public TextMatchingExerciseRule(TextMatchingExercise exercise, Rule rule) {
        super(exercise, rule);
    }

    public TextMatchingExerciseRule() {
    }

    @Override
    public void merge(TextMatchingExerciseRule entity) {

    }
}

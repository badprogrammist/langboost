package ru.langboost.domain.course.exercise;

import ru.langboost.domain.rule.Rule;

/**
 * Created by Ildar Gafarov on 12.12.15.
 */
public class RuleCheckingResult {

    private Rule rule;

    private double weight;

    public RuleCheckingResult(Rule rule, double weight) {
        this.rule = rule;
        this.weight = weight;
    }

    public Rule getRule() {
        return rule;
    }

    public double getWeight() {
        return weight;
    }
}

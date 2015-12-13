package ru.langboost.domain.course.exercise;

import ru.langboost.domain.rule.Rule;

import java.util.List;

/**
 * Created by Ildar Gafarov on 12.12.15.
 */
public class ExerciseCheckingResult {

    private List<RuleCheckingResult> ruleCheckingResults;

    public ExerciseCheckingResult(List<RuleCheckingResult> ruleCheckingResults) {
        this.ruleCheckingResults = ruleCheckingResults;
    }

    public List<RuleCheckingResult> getRuleCheckingResults() {
        return ruleCheckingResults;
    }
}

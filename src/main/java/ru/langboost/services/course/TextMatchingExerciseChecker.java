package ru.langboost.services.course;

import org.springframework.stereotype.Service;
import ru.langboost.domain.course.exercise.*;
import ru.langboost.services.ServiceException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ildar Gafarov on 12.12.15.
 */
@Service("textMatching")
public class TextMatchingExerciseChecker implements ExerciseChecker<TextMatchingPerformingResult> {

    public final static double RULE_WEIGHT = 1.0;

    @Override
    public ExerciseCheckingResult check(TextMatchingPerformingResult performingResult) throws ExerciseCheckingException {
        String performing = process(performingResult.getPerformingText());
        String expected = process(performingResult.getExercise().getTarget());
        boolean equal = performing.equalsIgnoreCase(expected);
        if(equal) {
            List<RuleCheckingResult> ruleResults = new ArrayList<>();
            for(TextMatchingExerciseRule exerciseRule : performingResult.getExercise().getRules()) {
                ruleResults.add(new RuleCheckingResult(exerciseRule.getRule(),RULE_WEIGHT));
            }
            return new ExerciseCheckingResult(ruleResults);
        } else {
            throw new ExerciseCheckingException();
        }
    }

    private String process(String value) {
        return value.trim().replace(",","").replace(".","").replace(" ","").replace("?","").replace("!","");
    }


}

package ru.langboost.services.rule;

import ru.langboost.domain.rule.Rule;
import ru.langboost.services.ServiceException;

import java.util.List;

/**
 * Created by Ildar Gafarov on 16.12.15.
 */
public interface RuleService {

    public Rule createRule(String title, String content) throws ServiceException;

    public Rule getRule(Long id);

    public List<Rule> getRules();
}

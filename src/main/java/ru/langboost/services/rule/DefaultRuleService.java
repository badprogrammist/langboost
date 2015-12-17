package ru.langboost.services.rule;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.langboost.domain.rule.Rule;
import ru.langboost.domain.rule.RuleRepository;
import ru.langboost.services.ServiceException;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Ildar Gafarov on 16.12.15.
 */
@Service
public class DefaultRuleService implements RuleService {

    @Inject
    private RuleRepository ruleRepository;

    @Transactional
    @Override
    public Rule createRule(String title, String content) throws ServiceException {
        if(title == null || title.isEmpty()) {
            throw new ServiceException("Не указано название!");
        }
        if(content == null || content.isEmpty()) {
            throw new ServiceException("Отсутсвует формулировка!");
        }
        Rule rule = new Rule(title,content);
        rule = ruleRepository.store(rule);
        return rule;
    }

    @Override
    public Rule getRule(Long id) {
        return ruleRepository.get(id);
    }

    @Override
    public List<Rule> getRules() {
        return ruleRepository.getAll();
    }

}

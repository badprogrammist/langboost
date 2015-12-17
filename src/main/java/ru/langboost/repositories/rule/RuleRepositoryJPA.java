package ru.langboost.repositories.rule;

import org.springframework.stereotype.Repository;
import ru.langboost.domain.dictionary.Dictionary;
import ru.langboost.domain.dictionary.Word;
import ru.langboost.domain.dictionary.WordRepository;
import ru.langboost.domain.rule.Rule;
import ru.langboost.domain.rule.RuleRepository;
import ru.langboost.repositories.AbstractRepositoryJPA;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

/**
 * Created by Ildar Gafarov on 09.12.15.
 */
@Repository
public class RuleRepositoryJPA extends AbstractRepositoryJPA<Rule> implements RuleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public RuleRepositoryJPA() {
        super(Rule.class);
    }


    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

}

package ru.langboost.domain.Dictionary;

import ru.langboost.domain.EntityRepository;

import java.util.List;

/**
 * Created by Ildar Gafarov on 09.12.15.
 */
public interface WordRepository extends EntityRepository<Word> {
    List<Word> findAll(Dictionary dictionary);
}

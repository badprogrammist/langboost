package ru.langboost.services.dictionary;

import ru.langboost.domain.profile.Profile;
import ru.langboost.domain.Dictionary.Word;
import ru.langboost.domain.Dictionary.Dictionary;
import ru.langboost.services.ServiceException;

import java.util.List;

/**
 * Created by Ildar Gafarov on 09.12.15.
 */
public interface DictionaryService {
    Dictionary get(Long id);
    List<Dictionary> getDictionaries(Profile profile);
    Dictionary getDefaultDictionary(Profile profile) throws ServiceException;
    Dictionary createNewDictionary(String title, Profile profile) throws ServiceException;
    Word addWord(String content, Dictionary dictionary) throws ServiceException;
    List<Word> getWords(Dictionary dictionary);
}

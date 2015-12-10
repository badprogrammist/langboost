package ru.langboost.services.dictionary;

import ru.langboost.domain.profile.Profile;
import ru.langboost.domain.dictionary.Word;
import ru.langboost.domain.dictionary.Dictionary;
import ru.langboost.services.ServiceException;

import java.util.List;

/**
 * Created by Ildar Gafarov on 09.12.15.
 */
public interface DictionaryService {
    Dictionary getDictionary(Long id);
    List<Dictionary> getDictionaries(Profile profile);
    Dictionary getDefaultDictionary(Profile profile) throws ServiceException;
    Dictionary createNewDictionary(String title, Profile profile) throws ServiceException;
    Word addWord(String content, Dictionary dictionary) throws ServiceException;
    List<Word> getWords(Dictionary dictionary);
}

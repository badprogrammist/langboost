package ru.langboost.services.dictionary;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.langboost.domain.profile.Profile;
import ru.langboost.domain.dictionary.Word;
import ru.langboost.domain.dictionary.WordRepository;
import ru.langboost.domain.dictionary.Dictionary;
import ru.langboost.domain.dictionary.DictionaryRepository;
import ru.langboost.services.ServiceException;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Ildar Gafarov on 09.12.15.
 */
@Service
public class DefaultDictionaryService implements DictionaryService {

    public static final String WORDS_SET_DEFAULT_TITLE = "Мой словарь";

    @Inject
    private DictionaryRepository dictionaryRepository;

    @Inject
    private WordRepository wordRepository;


    @Override
    @Transactional
    public Dictionary getDefaultDictionary(Profile profile) throws ServiceException {
        if(profile == null) {
            throw new ServiceException("Для получения словаря по умолчанию нужен профиль");
        }
        List<Dictionary> dictionaries = dictionaryRepository.findByProfile(profile,true);
        Dictionary defaulted;
        if(dictionaries == null || dictionaries.isEmpty()) {
            defaulted = new Dictionary(WORDS_SET_DEFAULT_TITLE,profile);
            defaulted.setDefaulted(true);
            dictionaryRepository.store(defaulted);
        } else {
            defaulted = dictionaries.get(0);
        }
        return defaulted;
    }

    @Override
    @Transactional
    public Dictionary createNewDictionary(String title, Profile profile) throws ServiceException {
        if(profile == null) {
            throw new ServiceException("Для создания словаря нужен профиль");
        }
        if(title == null || title.isEmpty()) {
            throw new ServiceException("Для создания словаря нужно название");
        }
        Dictionary set = new Dictionary(title,profile);
        dictionaryRepository.store(set);
        return set;
    }

    @Override
    @Transactional
    public Word addWord(String content, Dictionary set) throws ServiceException {
        if(set == null) {
            throw new ServiceException("Не указан словарь");
        }
        if(content == null || content.isEmpty()) {
            throw new ServiceException("Нет слова для добавления");
        }
        Word word = new Word(content, set);
        wordRepository.store(word);
        return word;
    }

    @Override
    public List<Dictionary> getDictionaries(Profile profile) {
        return dictionaryRepository.findByProfile(profile);
    }

    @Override
    public Dictionary getDictionary(Long id) {
        return dictionaryRepository.get(id);
    }

    @Override
    public List<Word> getWords(Dictionary dictionary) {
        return wordRepository.findAll(dictionary);
    }
}

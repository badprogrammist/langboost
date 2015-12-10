package ru.langboost.domain.Dictionary;

import ru.langboost.domain.EntityRepository;
import ru.langboost.domain.profile.Profile;

import java.util.List;

/**
 * Created by Ildar Gafarov on 09.12.15.
 */
public interface DictionaryRepository extends EntityRepository<Dictionary> {

    List<Dictionary> findByProfile(Profile profile, boolean defaulted);
    List<Dictionary> findByProfile(Profile profile);

}

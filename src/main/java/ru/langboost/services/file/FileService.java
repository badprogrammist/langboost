package ru.langboost.services.file;

import ru.langboost.domain.file.File;

/**
 * Created by Ildar Gafarov on 09.12.15.
 */
public interface FileService {
    File get(Long id);
}

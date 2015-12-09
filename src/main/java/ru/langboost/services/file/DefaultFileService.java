/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.langboost.services.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.langboost.domain.EntityRepository;
import ru.langboost.domain.file.File;
import ru.langboost.domain.file.FileRepository;
import ru.langboost.services.AbstractService;

import javax.inject.Inject;

/**
 *
 * @author bad
 */

@Service
@Transactional
public class DefaultFileService extends AbstractService<File> implements FileService {

    @Inject
    private FileRepository fileRepository;



    @Override
    protected EntityRepository getRepository() {
        return fileRepository;
    }
    
}

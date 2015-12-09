/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.langboost.controllers.file;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import ru.langboost.domain.file.File;
import ru.langboost.services.file.DefaultFileService;
import ru.langboost.services.file.FileService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author bad
 */
@Controller
public class FileController {

    public static final String SESSION_FILES_NAME = "session_files";
    public static final String SESSION_FILES_PARAMETER_NAME = "session";

    @Inject
    private FileService fileService;

    @RequestMapping(value = "/file/upload", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    List<File> upload(MultipartHttpServletRequest request, HttpServletResponse response) {
        List<File> files = new LinkedList<>();
        Iterator<String> fileNamesIterator = request.getFileNames();
        MultipartFile multipartFile = null;
        while (fileNamesIterator.hasNext()) {
            try {
                multipartFile = request.getFile(fileNamesIterator.next());
                String fileName = multipartFile.getOriginalFilename();
                String contentType = multipartFile.getContentType();
                byte[] source = multipartFile.getBytes();
                File file = new File(source, contentType, fileName);
                files.add(file);
            } catch (IOException ex) {
            }
        }
        request.getSession().setAttribute(SESSION_FILES_NAME, files);
        return files;
    }

    @RequestMapping(value = "/file/{index}/session", method = RequestMethod.GET)
    public void get(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer index) {
        List<File> files = (List<File>) request.getSession().getAttribute(SESSION_FILES_NAME);
        if (files != null && !files.isEmpty()) {
            File file = files.get(index);
            try {
                if (file != null) {
                    response.setContentType(file.getContentType());
                    response.setHeader("Content-disposition", "attachment; filename=\"" + file.getFileName() + "\"");
                    FileCopyUtils.copy(file.getSource(), response.getOutputStream());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping(value = "/file/{id}", method = RequestMethod.GET)
    public void get(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
        File file = fileService.get(id);
        try {
            if (file != null) {
                response.setContentType(file.getContentType());
                response.setHeader("Content-disposition", "attachment; filename=\"" + file.getFileName() + "\"");
                response.setHeader("Accept-Ranges", "bytes");
                response.setHeader("Content-Length", String.valueOf(file.getSource().length));
                FileCopyUtils.copy(file.getSource(), response.getOutputStream());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

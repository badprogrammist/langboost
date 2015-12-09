/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.langboost.controllers.file;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;
import ru.langboost.domain.file.File;

/**
 *
 * @author bad
 */
public class FileHelper {

    //private final static String STREAM_IMAGE_TYPE = "application/octet-stream";
    
    public static File createFile(MultipartFile multipartFile) throws IOException {
        String fileName = generateFileName(multipartFile);
        String contentType = generateContentType(multipartFile);
        byte[] source = multipartFile.getBytes();
        File file = new File(source, contentType, fileName);
        return file;
    }

    private static String generateFileName(MultipartFile multipartFile) {
        if (multipartFile.getOriginalFilename() == null || "".equals(multipartFile.getOriginalFilename())) {
            return File.DEFAULT_FILENAME + "." + File.DEFAULT_IMAGE_FORMAT;
        } else {
            return multipartFile.getOriginalFilename();
        }
    }
    
    private static String generateContentType(MultipartFile multipartFile) {
//        if(multipartFile.getContentType().equals(STREAM_IMAGE_TYPE)) {
//            return  File.DEFAULT_IMAGE_CONTENT_TYPE;
//        } else {
            return multipartFile.getContentType();
//        }
    }

}

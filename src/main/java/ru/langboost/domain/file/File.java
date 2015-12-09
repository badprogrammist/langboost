/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.langboost.domain.file;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import ru.langboost.domain.AbstractEntity;

/**
 *
 * @author bad
 */
@Entity
@Table(name = "files")
@Cacheable
@NamedQueries({
    @NamedQuery(name = "File.findAll",
            query = "Select c from File c")
})
@JsonIgnoreProperties({"source"}) 
public class File extends AbstractEntity<File> {
    
    public static final String DEFAULT_FILENAME = "noname";
    public static final String DEFAULT_IMAGE_FORMAT = "jpg";
    public static final String DEFAULT_IMAGE_CONTENT_TYPE = "image/jpeg";
    
    
    @Column(name = "source", nullable = false)
    private byte[] source;
    
    @Column(name = "content_type",nullable = false)
    private String contentType;
    
    @Column(name = "filename",nullable = false)
    private String fileName;

    public File() {
    }

    public File(byte[] source, String contentType, String fileName) {
        this.source = source;
        this.contentType = contentType;
        this.fileName = fileName;
    }

    public byte[] getSource() {
        return source;
    }

    public void setSource(byte[] source) {
        this.source = source;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void merge(File entity) {
        this.source = entity.getSource();
        this.fileName = entity.getFileName();
        this.contentType = entity.getContentType();
    }
    
    
    
}

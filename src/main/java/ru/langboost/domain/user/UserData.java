/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.langboost.domain.user;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import ru.langboost.domain.file.File;
import ru.langboost.domain.file.FileHolder;

/**
 *
 * @author Ильдар
 */
@Embeddable
public class UserData implements Serializable,FileHolder {

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "icon_id")
    private File icon;
    
    @Column(name = "name",nullable = false)
    private String name;
    
    @Column(name = "lastname",nullable = false)
    private String lastname;
    
    @Column(name = "patronymic")
    private String patronymic;
    
    public UserData() {
    }

    public UserData(String name, String lastname, String patronymic,File icon) {
        this.name = name;
        this.lastname = lastname;
        this.patronymic = patronymic;
        this.icon = icon;
    }

    public String getFullName() {
        StringBuilder sb = new StringBuilder();
        if(name != null && !name.isEmpty()) {
            sb.append(name).append(" ");
        }
        if(lastname != null && !lastname.isEmpty()) {
            sb.append(lastname).append(" ");
        }
        if(patronymic != null && !patronymic.isEmpty()) {
            sb.append(patronymic).append(" ");
        }
        return sb.toString();
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public File getIcon() {
        return icon;
    }

    public void setIcon(File icon) {
        this.icon = icon;
    }

    @Override
    public void setFile(File file) {
        this.icon = file;
    }

    @Override
    public File getFile() {
        return icon;
    }
    
    
}

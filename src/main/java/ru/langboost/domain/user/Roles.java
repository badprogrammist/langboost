/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.langboost.domain.user;

import ru.langboost.domain.EnumType;




/**
 *
 * @author Ильдар
 */
public enum Roles implements EnumType {
    
    ROLE_USER("Пользователь"),
    ROLE_ADMIN("Администратор");
    
    Roles(String title) {
        this.title = title;
    }
    
    private final String title;

    @Override
    public String getTitle() {
        return title;
    }
    
    
    
}

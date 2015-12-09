/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.langboost.controllers.message;

/**
 *
 * @author bad
 */
public enum MessageType {
    INFO,
    DANGER,
    WARNING,
    SUCCESS;
    
    public String toLowerCase() {
        return this.name().toLowerCase();
    }
    
}

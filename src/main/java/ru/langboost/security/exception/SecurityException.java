/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.langboost.security.exception;

/**
 *
 * @author Ильдар
 */
public class SecurityException extends Exception {

    /**
     * Creates a new instance of <code>SecurityException</code> without detail
     * message.
     */
    public SecurityException() {
    }

    /**
     * Constructs an instance of <code>SecurityException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public SecurityException(String msg) {
        super(msg);
    }
}

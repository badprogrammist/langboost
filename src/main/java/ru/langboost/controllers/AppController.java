/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.langboost.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.langboost.security.AuthenticationService;
import ru.langboost.security.DefaultAuthenticationService;

import javax.inject.Inject;

/**
 * @author bad
 */
@Controller
public class AppController {

    @Inject
    private AuthenticationService authenticationService;

    @RequestMapping(value = "/", method = {RequestMethod.GET})
    public ModelAndView index() {
        ModelAndView model = new ModelAndView();
        model.setViewName("index");
        return model;
    }

    @RequestMapping(value = "/404", method = {RequestMethod.GET})
    public ModelAndView page404() {
        ModelAndView model = new ModelAndView();
        model.setViewName("404");
        return model;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.langboost.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.langboost.controllers.course.CourseController;
import ru.langboost.domain.course.Course;
import ru.langboost.domain.profile.ProfileCourse;
import ru.langboost.domain.user.Roles;
import ru.langboost.domain.user.User;
import ru.langboost.security.AuthenticationService;
import ru.langboost.security.DefaultAuthenticationService;
import ru.langboost.services.ServiceException;
import ru.langboost.services.course.CourseService;
import ru.langboost.services.profile.ProfileService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author bad
 */
@Controller
public class AppController extends AbstractController {

    @Inject
    private AuthenticationService authenticationService;

    @Inject
    private CourseService courseService;

    @Inject
    private ProfileService profileService;

    @RequestMapping(value = "/", method = {RequestMethod.GET})
    public String index(Model model, RedirectAttributes redirectAttributes) {
        if(authenticationService.isAuthenticated()) {
            User user = authenticationService.getPrincipal();
            try {
                if(user.hasRole(Roles.ROLE_AUTHOR)) {
                    model.addAttribute("courses",courseService.getCourses(user));
                    return "course/courses";
                }
                if(user.hasRole(Roles.ROLE_USER)) {
                    List<Course> profileCourses = new ArrayList<>();
                    for(ProfileCourse profileCourse : profileService.getProfileCourses(user.getProfile())) {
                        profileCourses.add(profileCourse.getCourse());
                    }
                    model.addAttribute("courses",profileCourses);
                    return "profile/profile";
                }
            } catch (ServiceException e) {
                addFlashMessage(e,redirectAttributes);
            }
        }
        return "index";
    }

    @RequestMapping(value = "/404", method = {RequestMethod.GET})
    public ModelAndView page404() {
        ModelAndView model = new ModelAndView();
        model.setViewName("404");
        return model;
    }

}

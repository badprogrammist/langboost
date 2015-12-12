package ru.langboost.controllers.profile;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.langboost.controllers.AbstractController;
import ru.langboost.controllers.course.CourseController;
import ru.langboost.controllers.message.Message;
import ru.langboost.controllers.message.MessageType;
import ru.langboost.domain.course.Course;
import ru.langboost.domain.user.User;
import ru.langboost.security.AuthenticationService;
import ru.langboost.services.ServiceException;
import ru.langboost.services.course.CourseService;
import ru.langboost.services.profile.ProfileService;

import javax.inject.Inject;

/**
 * Created by Ildar Gafarov on 12.12.15.
 */
@Controller
public class ProfileController extends AbstractController {

    @Inject
    private CourseService courseService;

    @Inject
    private ProfileService profileService;

    @Inject
    private AuthenticationService authenticationService;

    @Secured("ROLE_USER")
    @RequestMapping(value = "/course/attach", method = RequestMethod.POST)
    public String attachCourse(Long courseId, RedirectAttributes redirectAttributes) {
        Course course = courseService.getCourse(courseId);
        if(course == null) {
            addFlashMessage(CourseController.courseNotFound(), redirectAttributes);
            return CourseController.gotoCourses();
        }
        User user = authenticationService.getPrincipal();
        try {
            profileService.attachCourse(user.getProfile(),course);
            addFlashMessage(new Message(MessageType.SUCCESS,"Курс успешно прикреплен!"),redirectAttributes);
        } catch (ServiceException ex) {
            addFlashMessage(ex,redirectAttributes);
        }
        return CourseController.gotoCourse(courseId);
    }


}

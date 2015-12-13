package ru.langboost.controllers.course;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.langboost.controllers.AbstractController;
import ru.langboost.controllers.message.Message;
import ru.langboost.controllers.message.MessageType;
import ru.langboost.domain.course.Course;
import ru.langboost.domain.course.Unit;
import ru.langboost.domain.course.exercise.ExerciseType;
import ru.langboost.domain.user.User;
import ru.langboost.security.AuthenticationService;
import ru.langboost.services.ServiceException;
import ru.langboost.services.course.CourseService;
import ru.langboost.services.course.ExerciseService;
import ru.langboost.services.profile.ProfileService;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Ildar Gafarov on 09.12.15.
 */
@Controller
@RequestMapping
public class UnitController extends AbstractController {

    @Inject
    private AuthenticationService authenticationService;

    @Inject
    private CourseService courseService;

    @Inject
    private ProfileService profileService;

    @Inject
    private ExerciseService exerciseService;

    @Secured("ROLE_AUTHOR")
    @RequestMapping(value = "/unit/new/{courseId}", method = RequestMethod.GET)
    public String newUnit(@PathVariable Long courseId, Model model, RedirectAttributes redirectAttributes) {
        Course course = courseService.getCourse(courseId);
        if(course == null) {
            addFlashMessage(CourseController.courseNotFound(), redirectAttributes);
            return CourseController.gotoCourses();
        }
        model.addAttribute("course",course);
        model.addAttribute("unit",new Unit());
        return "unit/new_unit";
    }

    @Secured("ROLE_AUTHOR")
    @RequestMapping(value = "/unit/save", method = RequestMethod.POST)
    public String saveUnit(String title, String description, Long courseId, RedirectAttributes redirectAttributes) {
        if(title == null || title.isEmpty()) {
            addFlashMessage(new Message(MessageType.DANGER,"Введите название юнита!"), redirectAttributes);
            return CourseController.gotoCourse(courseId);
        }
        Course course = courseService.getCourse(courseId);
        if(course == null) {
            addFlashMessage(CourseController.courseNotFound(), redirectAttributes);
            return CourseController.gotoCourses();
        }

        try {
            courseService.createUnit(title, description, course);
            addFlashMessage(new Message(MessageType.SUCCESS,"Юнит '"+title+"' успешно добавлен!"),redirectAttributes);
        } catch (ServiceException e) {
            addFlashMessage(e, redirectAttributes);
        }

        return CourseController.gotoCourse(courseId);
    }

    @RequestMapping(value = "/unit/{id}", method = RequestMethod.GET)
    public String unit(Model model, @PathVariable Long id, RedirectAttributes redirectAttributes) {
        Unit unit = courseService.getUnit(id);
        if(unit == null) {
            addFlashMessage(unitNotFound(), redirectAttributes);
            return CourseController.gotoCourses();
        }
        boolean editable = false;
        if(authenticationService.isAuthenticated()) {
            User user = authenticationService.getPrincipal();
            editable = unit.getCourse().isOwner(user);
        }
        model.addAttribute("unit",unit);
        model.addAttribute("isEditable",editable);
        model.addAttribute("exerciseTypes", Arrays.asList(ExerciseType.values()));
        model.addAttribute("exercises", exerciseService.getExercises(unit));
        return "unit/unit";
    }

    public static String gotoUnit(Long id) {
        return "redirect:/unit/"+id;
    }

    public static Message unitNotFound() {
        return new Message(MessageType.DANGER,"Юнит не найден");
    }

}

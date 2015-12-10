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
import ru.langboost.domain.dictionary.Dictionary;
import ru.langboost.domain.user.User;
import ru.langboost.security.AuthenticationService;
import ru.langboost.services.ServiceException;
import ru.langboost.services.course.CourseService;
import ru.langboost.services.dictionary.DictionaryService;
import ru.langboost.services.profile.ProfileService;

import javax.inject.Inject;
import java.util.Collections;

/**
 * Created by Ildar Gafarov on 09.12.15.
 */
@Controller
@RequestMapping
public class CourseController extends AbstractController {

    @Inject
    private AuthenticationService authenticationService;

    @Inject
    private CourseService courseService;

    @Secured("ROLE_AUTHOR")
    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    public String courses(Model model, RedirectAttributes redirectAttributes) {
        User author = authenticationService.getPrincipal();
        try {
            model.addAttribute("courses",courseService.getCourses(author));
        } catch (ServiceException e) {
            addFlashMessage(e,redirectAttributes);
        }
        return "course/courses";
    }

    @Secured("ROLE_AUTHOR")
    @RequestMapping(value = "/course/new", method = RequestMethod.GET)
    public String newCourse(Model model) {
        model.addAttribute("course",new Course());
        return "course/new_course";
    }

    @Secured("ROLE_AUTHOR")
    @RequestMapping(value = "/course/edit/{id}", method = RequestMethod.GET)
    public String editCourse(Model model, @PathVariable Long id, RedirectAttributes redirectAttributes) {
        Course course = courseService.getCourse(id);
        if(course == null) {
            addFlashMessage(new Message(MessageType.DANGER,"Курс не найден"), redirectAttributes);
            return "redirect:/courses";
        }
        User user = authenticationService.getPrincipal();
        if(course.isOwner(user)) {
            model.addAttribute("course",course);
            return "course/edit_course";
        } else {
            addFlashMessage(new Message(MessageType.DANGER,"У Вас нет прав для данного действия"), redirectAttributes);
            return "redirect:/courses";
        }
    }

    @Secured("ROLE_AUTHOR")
    @RequestMapping(value = "/course/save", method = RequestMethod.POST)
    public String saveCourse(String title, String description, RedirectAttributes redirectAttributes) {
        if(title == null || title.isEmpty()) {
            addFlashMessage(new Message(MessageType.DANGER,"Укажите название курса!"), redirectAttributes);
        } else {
            User user = authenticationService.getPrincipal();
            try {
                courseService.createCourse(title, description, user);
                addFlashMessage(new Message(MessageType.SUCCESS,"Курс '"+title+"' успешно создан!"),redirectAttributes);
            } catch (ServiceException e) {
                addFlashMessage(e, redirectAttributes);
            }
        }
        return "redirect:/courses";
    }

    @Secured("ROLE_AUTHOR")
    @RequestMapping(value = "/course/update", method = RequestMethod.POST)
    public String updateCourse(String title, String description,Long id, RedirectAttributes redirectAttributes) {
        if(title == null || title.isEmpty()) {
            addFlashMessage(new Message(MessageType.DANGER,"Укажите название курса!"), redirectAttributes);
            return "redirect:/course/edit/"+id;
        } else {
            User user = authenticationService.getPrincipal();
            try {
                courseService.updateCourse(title, description, id, user);
                addFlashMessage(new Message(MessageType.SUCCESS,"Курс '"+title+"' успешно обновлен!"),redirectAttributes);
            } catch (ServiceException e) {
                addFlashMessage(e, redirectAttributes);
                return "redirect:/courses";
            }
        }
        return "redirect:/course/"+id;
    }

    @Secured("ROLE_AUTHOR")
    @RequestMapping(value = "/course/{id}", method = RequestMethod.GET)
    public String course(Model model, @PathVariable Long id, RedirectAttributes redirectAttributes) {
        Course course = courseService.getCourse(id);
        if(course == null) {
            addFlashMessage(new Message(MessageType.DANGER,"Курс не найден"), redirectAttributes);
            return "redirect:/courses";
        }
        User user = authenticationService.getPrincipal();
        model.addAttribute("course",course);
        model.addAttribute("editable",course.isOwner(user));
        try {
            model.addAttribute("units",courseService.getUnits(course));
        } catch (ServiceException e) {
            addFlashMessage(new Message(MessageType.DANGER,"Произошла ошибка при получении юнитов"), redirectAttributes);
            model.addAttribute("units", Collections.emptyList());
        }
        return "course/course";
    }

    @Secured("ROLE_AUTHOR")
    @RequestMapping(value = "/unit/new/{courseId}", method = RequestMethod.GET)
    public String newUnit(@PathVariable Long courseId, Model model, RedirectAttributes redirectAttributes) {
        Course course = courseService.getCourse(courseId);
        if(course == null) {
            addFlashMessage(new Message(MessageType.DANGER,"Курс не найден"), redirectAttributes);
            return "redirect:/courses";
        }
        model.addAttribute("course",course);
        model.addAttribute("unit",new Unit());
        return "course/new_unit";
    }

    @Secured("ROLE_AUTHOR")
    @RequestMapping(value = "/unit/save", method = RequestMethod.POST)
    public String saveUnit(String title, String description, Long courseId, RedirectAttributes redirectAttributes) {
        if(title == null || title.isEmpty()) {
            addFlashMessage(new Message(MessageType.DANGER,"Введите название юнита!"), redirectAttributes);
            return "redirect:/course/"+courseId;
        }
        Course course = courseService.getCourse(courseId);
        if(course == null) {
            addFlashMessage(new Message(MessageType.DANGER,"Курс не найден!"), redirectAttributes);
            return "redirect:/courses";
        }
        try {
            courseService.createUnit(title, description, course);
            addFlashMessage(new Message(MessageType.SUCCESS,"Юнит '"+title+"' успешно добавлен!"),redirectAttributes);
        } catch (ServiceException e) {
            addFlashMessage(e, redirectAttributes);
        }

        return "redirect:/course/"+courseId;
    }

    @Secured("ROLE_AUTHOR")
    @RequestMapping(value = "/unit/{id}", method = RequestMethod.GET)
    public String unit(Model model, @PathVariable Long id, RedirectAttributes redirectAttributes) {
        Unit unit = courseService.getUnit(id);
        if(unit == null) {
            addFlashMessage(new Message(MessageType.DANGER,"Юнит не найден"), redirectAttributes);
            return "redirect:/courses";
        }
        model.addAttribute("unit",unit);
        return "course/unit";
    }

}

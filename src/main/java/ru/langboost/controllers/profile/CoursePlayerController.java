package ru.langboost.controllers.profile;

import org.springframework.context.annotation.Scope;
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
import ru.langboost.domain.course.exercise.ExerciseType;
import ru.langboost.domain.course.exercise.TextMatchingPerformingResult;
import ru.langboost.domain.user.User;
import ru.langboost.security.AuthenticationService;
import ru.langboost.services.ServiceException;
import ru.langboost.services.course.CoursePlayer;
import ru.langboost.services.course.CourseService;
import ru.langboost.services.course.ExerciseCheckingException;

import javax.inject.Inject;

/**
 * Created by Ildar Gafarov on 14.12.15.
 */
@Controller
@Scope("session")
public class CoursePlayerController extends AbstractController {

    @Inject
    private CoursePlayer coursePlayer;

    @Inject
    private CourseService courseService;

    @Inject
    private AuthenticationService authenticationService;

    @Secured("ROLE_USER")
    @RequestMapping(value = "/course/play", method = RequestMethod.POST)
    public String playCourse(Long courseId, RedirectAttributes redirectAttributes) {
        Course course = courseService.getCourse(courseId);
        if(course == null) {
            addFlashMessage(CourseController.courseNotFound(), redirectAttributes);
            return CourseController.gotoCourses();
        }
        User user = authenticationService.getPrincipal();
        try {
            coursePlayer.abort();
            coursePlayer.init(course,user.getProfile());
        } catch (ServiceException ex) {
            addFlashMessage(ex,redirectAttributes);
            return CourseController.gotoCourse(courseId);
        }
        return gotoPlayExercise();
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/course/play/exercise", method = RequestMethod.GET)
    public String playExercise(Model model, RedirectAttributes redirectAttributes) {
        if(coursePlayer.isInitialized()) {
            model.addAttribute("exercise",coursePlayer.getExercise());
            model.addAttribute("unit",coursePlayer.getUnit());
            model.addAttribute("course",coursePlayer.getCourse());
            model.addAttribute("performingResult",coursePlayer.getExercise().createEmptyPerformingResult());
            return "player/exercise";
        } else {
            addFlashMessage(new Message(MessageType.DANGER, "Курс не запущен!"), redirectAttributes);
            return CourseController.gotoCourses();
        }
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/course/play/unit/result", method = RequestMethod.GET)
    public String unitResult(Model model, RedirectAttributes redirectAttributes) {
        if(coursePlayer.isInitialized()) {
            model.addAttribute("unit",coursePlayer.getUnit());
            model.addAttribute("course",coursePlayer.getCourse());
            model.addAttribute("hasNextUnit",coursePlayer.hasNextUnit());
            return "player/unit_result";
        } else {
            addFlashMessage(new Message(MessageType.DANGER, "Курс не запущен!"), redirectAttributes);
            return CourseController.gotoCourses();
        }
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/course/play/course/result", method = RequestMethod.GET)
    public String courseResult(Model model, RedirectAttributes redirectAttributes) {
        if(coursePlayer.isInitialized()) {
            model.addAttribute("course",coursePlayer.getCourse());
            coursePlayer.abort();
            return "player/course_result";
        } else {
            addFlashMessage(new Message(MessageType.DANGER, "Курс не запущен!"), redirectAttributes);
            return CourseController.gotoCourses();
        }
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/course/play/unit/next", method = RequestMethod.POST)
    public String nextUnit(Model model, RedirectAttributes redirectAttributes) {
        if(coursePlayer.isInitialized()) {
            if(coursePlayer.hasNextUnit()) {
                try {
                    coursePlayer.nextUnit();
                    if(coursePlayer.hasNextExercise()) {
                        coursePlayer.nextExercise();
                        return gotoPlayExercise();
                    } else {
                        return gotoCourseResult();
                    }
                } catch (ServiceException e) {
                    addFlashMessage(e, redirectAttributes);
                    Course course = coursePlayer.getCourse();
                    coursePlayer.abort();
                    return CourseController.gotoCourse(course.getId());
                }
            } else {
                return gotoCourseResult();
            }
        } else {
            addFlashMessage(new Message(MessageType.DANGER, "Курс не запущен!"), redirectAttributes);
            return CourseController.gotoCourses();
        }
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/course/play/exercise/tm/check", method = RequestMethod.POST)
    public String tmCheck(String performingText, RedirectAttributes redirectAttributes) {
        if(performingText == null || performingText.isEmpty()) {
            addFlashMessage(new Message(MessageType.DANGER, "Введите перевод текста"),redirectAttributes);
            return gotoPlayExercise();
        }
        if(coursePlayer.isInitialized()) {
            if(coursePlayer.getExercise().getType() == ExerciseType.TEXT_MATCHING) {
                TextMatchingPerformingResult performingResult = (TextMatchingPerformingResult)coursePlayer.getExercise().createEmptyPerformingResult();
                performingResult.setPerformingText(performingText);
                try {
                    coursePlayer.check(performingResult);
                    addFlashMessage(new Message(MessageType.SUCCESS, "Верно"),redirectAttributes);
                } catch (ExerciseCheckingException e) {
                    addFlashMessage(new Message(MessageType.DANGER, "Неправильный перевод"),redirectAttributes);
                    return gotoPlayExercise();
                }
                return gotoNextExercise(redirectAttributes);
            }
        } else {
            addFlashMessage(new Message(MessageType.DANGER, "Курс не запущен!"), redirectAttributes);
        }
        return CourseController.gotoCourses();
    }

    private String gotoNextExercise(RedirectAttributes redirectAttributes) {
        try {
            if(coursePlayer.hasNextExercise()) {
                coursePlayer.nextExercise();
                return gotoPlayExercise();
            } else {
                if(coursePlayer.hasNextUnit()) {
                    return gotoUnitResult();
                } else {
                    return gotoCourseResult();
                }
            }
        } catch (ServiceException ex) {
            addFlashMessage(ex,redirectAttributes);
            Course course = coursePlayer.getCourse();
            coursePlayer.abort();
            return CourseController.gotoCourse(course.getId());
        }
    }

    private static String gotoPlayExercise() {
        return "redirect:/course/play/exercise";
    }
    private static String gotoUnitResult() {
        return "redirect:/course/play/unit/result";
    }
    private static String gotoCourseResult() {
        return "redirect:/course/play/course/result";
    }
}

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
import ru.langboost.domain.course.exercise.AbstractExercise;
import ru.langboost.domain.course.exercise.ExerciseType;
import ru.langboost.domain.course.exercise.TextMatchingExercise;
import ru.langboost.security.AuthenticationService;
import ru.langboost.services.ServiceException;
import ru.langboost.services.course.CourseService;
import ru.langboost.services.course.ExerciseService;
import ru.langboost.services.profile.ProfileService;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ildar Gafarov on 12.12.15.
 */
@Controller
public class ExerciseController extends AbstractController {

    @Inject
    private AuthenticationService authenticationService;

    @Inject
    private CourseService courseService;

    @Inject
    private ProfileService profileService;

    @Inject
    private ExerciseService exerciseService;

    @Secured("ROLE_AUTHOR")
    @RequestMapping(value = "/exercise/new", method = RequestMethod.POST)
    public String saveUnit(ExerciseType type, Long unitId, RedirectAttributes redirectAttributes) {
        if(type == null) {
            addFlashMessage(new Message(MessageType.DANGER,"Выберите тип упражнения!"), redirectAttributes);
            return UnitController.gotoUnit(unitId);
        }
        Unit unit = courseService.getUnit(unitId);
        if(unit == null) {
            addFlashMessage(UnitController.unitNotFound(), redirectAttributes);
            return CourseController.gotoCourses();
        }

        try {
            AbstractExercise exercise = exerciseService.createExercise(type, unit);
            addFlashMessage(new Message(MessageType.SUCCESS,"Упражнение успешно создано"),redirectAttributes);
            return gotoExerciseEdit(exercise.getId(),type);
        } catch (ServiceException e) {
            addFlashMessage(e, redirectAttributes);
        }

        return UnitController.gotoUnit(unitId);
    }

    @Secured("ROLE_AUTHOR")
    @RequestMapping(value = "/exercise/edit/{type}/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Long id,@PathVariable String type,Model model, RedirectAttributes redirectAttributes) {
        ExerciseType exerciseType = ExerciseType.getByCode(type);
        if(type == null) {
            addFlashMessage(new Message(MessageType.DANGER,"Не указан тип упражнения"), redirectAttributes);
            return CourseController.gotoCourses();
        }
        AbstractExercise exercise = exerciseService.getExercise(id,exerciseType);
        if(exercise == null) {
            addFlashMessage(exerciseNotFound(), redirectAttributes);
            return CourseController.gotoCourses();
        }
        model.addAttribute("exercise",exercise);
        return getViewByType(exerciseType,"edit");
    }

    @Secured("ROLE_AUTHOR")
    @RequestMapping(value = "/exercise/update/tm", method = RequestMethod.POST)
    public String updateTextMatchingExercise(String target, String translate, Long exerciseId, RedirectAttributes redirectAttributes) {
        if(target == null || target.isEmpty()) {
            addFlashMessage(new Message(MessageType.DANGER,"Не указан текст на целевом языке!"),redirectAttributes);
            return gotoExerciseEdit(exerciseId,ExerciseType.TEXT_MATCHING);
        }
        if(translate == null || translate.isEmpty()) {
            addFlashMessage(new Message(MessageType.DANGER,"Не указан перевод!"),redirectAttributes);
            return gotoExerciseEdit(exerciseId,ExerciseType.TEXT_MATCHING);
        }
        TextMatchingExercise newData = new TextMatchingExercise(null,target,translate);
        try {
            AbstractExercise exercise = exerciseService.updateExercise(newData,ExerciseType.TEXT_MATCHING, exerciseId);
            addFlashMessage(new Message(MessageType.SUCCESS,"Упражнение успешно обновленно"),redirectAttributes);
            return UnitController.gotoUnit(exercise.getUnit().getId());
        } catch (ServiceException e) {
            addFlashMessage(e, redirectAttributes);
            return CourseController.gotoCourses();
        }
    }

    @Secured("ROLE_AUTHOR")
    @RequestMapping(value = "/exercise/update/order", method = RequestMethod.POST)
    public String updateTextMatchingExercise(String[] order, Long unitId, RedirectAttributes redirectAttributes) {
        if(order == null || order.length == 0) {
            addFlashMessage(new Message(MessageType.DANGER,"Не указан текст на целевом языке!"),redirectAttributes);
            return UnitController.gotoUnit(unitId);
        }
        Unit unit = courseService.getUnit(unitId);
        if(unit == null) {
            addFlashMessage(UnitController.unitNotFound(), redirectAttributes);
            return CourseController.gotoCourses();
        }
        Map<Long,Integer> indexedExercises = new HashMap<>();
        for(String exerciseWithOrder : order) {
            String[] exerciseIdAndOrder = exerciseWithOrder.split("#");
            if(exerciseIdAndOrder == null || exerciseIdAndOrder.length != 2) {
                addFlashMessage(new Message(MessageType.DANGER,"Произошла ошибка при вводе данных!"),redirectAttributes);
                return UnitController.gotoUnit(unitId);
            }
            Long exerciseId = Long.valueOf(exerciseIdAndOrder[0]);
            Integer index = Integer.valueOf(exerciseIdAndOrder[1]);
            indexedExercises.put(exerciseId, index);
        }
        try {
            exerciseService.updateOrder(indexedExercises,unit);
            addFlashMessage(new Message(MessageType.SUCCESS,"Порядок сохранен"),redirectAttributes);
        } catch (ServiceException e) {
            addFlashMessage(e, redirectAttributes);
        }
        return UnitController.gotoUnit(unitId);
    }

    private static String getViewByType(ExerciseType type, String suffix) {
        return "exercise/"+type.getCode()+"_"+suffix;
    }

    public static String gotoExerciseEdit(Long id, ExerciseType type) {
        return "redirect:/exercise/edit/"+type.getCode()+"/"+id;
    }

    public static Message exerciseNotFound() {
        return new Message(MessageType.DANGER,"Упражнение не найдено!");
    }

}

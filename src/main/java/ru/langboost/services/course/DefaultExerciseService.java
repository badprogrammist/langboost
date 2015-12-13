package ru.langboost.services.course;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.langboost.domain.course.Unit;
import ru.langboost.domain.course.exercise.*;
import ru.langboost.services.OrderComparator;
import ru.langboost.services.ServiceException;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by Ildar Gafarov on 12.12.15.
 */
@Service
public class DefaultExerciseService implements ExerciseService {

    @Inject
    private TextMatchingExerciseRepository textMatchingExerciseRepository;

    @Override
    @Transactional
    public AbstractExercise createExercise(ExerciseType type, Unit unit) throws ServiceException {
        AbstractExercise exercise = null;
        if(type == null) {
            throw  new ServiceException("Не указан тип упражнения!");
        }
        if(unit == null) {
            throw  new ServiceException("Не указан юнит!");
        }
        if(type == ExerciseType.TEXT_MATCHING) {
            exercise = new TextMatchingExercise(unit,"","");
            exercise = textMatchingExerciseRepository.store((TextMatchingExercise)exercise);
        }
        if(exercise == null) {
            throw new ServiceException("Невозможно создать упражнение! Тип не поддерживается.");
        }
        return exercise;
    }

    @Override
    @Transactional
    public AbstractExercise updateExercise(AbstractExercise newData,ExerciseType type, Long id) throws ServiceException {
        AbstractExercise oldExercise = null;
        if(type == ExerciseType.TEXT_MATCHING) {
            oldExercise = textMatchingExerciseRepository.get(id);
        }
        if(oldExercise == null) {
            throw new ServiceException("Упражнение не найдено!");
        }
        if(newData == null) {
            throw new ServiceException("Новые данные не корректны!");
        }
        oldExercise.merge(newData);
        return updateExercise(oldExercise);
    }

    @Override
    @Transactional
    public void updateOrder(Map<Long, Integer> indexedExercises, Unit unit) throws ServiceException {
        if(indexedExercises == null || indexedExercises.isEmpty()) {
            throw new ServiceException("Нет данных для установки порядка сортировки!");
        }
        if(unit == null) {
            throw  new ServiceException("Не указан юнит!");
        }
        List<AbstractExercise> exercises = getExercises(unit);
        if(exercises.size() != indexedExercises.size()) {
            throw new ServiceException("Порядок сортировки установлен не для всех упражнений!");
        }
        for(Long exerciseId : indexedExercises.keySet()) {
            Integer exerciseOrderIndex = indexedExercises.get(exerciseId);
            for(Long anotherExerciseId : indexedExercises.keySet()) {
                if(!exerciseId.equals(anotherExerciseId)) {
                    Integer anotherExerciseOrderIndex = indexedExercises.get(anotherExerciseId);
                    if(exerciseOrderIndex.equals(anotherExerciseOrderIndex)) {
                        throw new ServiceException("У некоторых упражнений установлен одинаковый порядок!");
                    }
                }
            }
        }
        for(Long exerciseId : indexedExercises.keySet()) {
            Integer exerciseOrderIndex = indexedExercises.get(exerciseId);
            AbstractExercise exercise = null;
            for(AbstractExercise exerciseItem : exercises) {
                if(exerciseId.equals(exerciseItem.getId())) {
                    exercise = exerciseItem;
                    break;
                }
            }
            if(exercise == null) {
                throw new ServiceException("Упражнение не найдено!");
            }
            exercise.setOrderNumber(exerciseOrderIndex);
            updateExercise(exercise);
        }
    }

    private AbstractExercise updateExercise(AbstractExercise exercise)  throws ServiceException {
        if(exercise == null) {
            throw new ServiceException("Упражнение не найдено!");
        }
        if(exercise.getType() == ExerciseType.TEXT_MATCHING) {
            return textMatchingExerciseRepository.update((TextMatchingExercise)exercise);
        }
        throw new ServiceException("Невозможно обновить упражнение! Тип не поддерживается.");
    }

    @Override
    public AbstractExercise getExercise(Long id, ExerciseType type) {
        if(type == ExerciseType.TEXT_MATCHING) {
            return textMatchingExerciseRepository.get(id);
        }
        return null;
    }

    @Override
    public List<AbstractExercise> getExercises(Unit unit) {
        List<AbstractExercise> exercises = new ArrayList<>();
        if(unit != null) {
            exercises.addAll(textMatchingExerciseRepository.findByUnit(unit));
        }
        Collections.sort(exercises, new OrderComparator());
        return exercises;
    }

}

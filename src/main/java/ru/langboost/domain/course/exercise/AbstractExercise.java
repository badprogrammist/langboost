package ru.langboost.domain.course.exercise;

import ru.langboost.domain.AbstractEntity;
import ru.langboost.domain.Sortable;
import ru.langboost.domain.course.Unit;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ildar Gafarov on 12.12.15.
 */
@MappedSuperclass
public abstract class AbstractExercise
        <E extends AbstractExercise,
        PR extends AbstractPerformingResult,
        AER extends AbstractExerciseRule> extends AbstractEntity<E> implements Sortable {

    @ManyToOne
    @JoinColumn(name = "unit_id",nullable = false)
    private Unit unit;

    @Column(name = "order_number")
    private int orderNumber;

    public AbstractExercise( Unit unit) {
        this.unit = unit;
    }

    public AbstractExercise() {

    }

    public abstract PR createEmptyPerformingResult();

    public abstract ExerciseType getType();

    public abstract List<AER> getRules();

    public abstract void setRules(List<AER> rules);

    @Override
    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}

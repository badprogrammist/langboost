package ru.langboost.services;

import ru.langboost.domain.Sortable;

import java.util.Comparator;

/**
 * Created by Ildar Gafarov on 12.12.15.
 */
public class OrderComparator implements Comparator<Sortable> {

    @Override
    public int compare(Sortable o1, Sortable o2) {
        return o1.getOrderNumber() - o2.getOrderNumber();
    }

}

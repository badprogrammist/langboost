package ru.langboost.domain.course;

import ru.langboost.domain.EntityRepository;

import java.util.List;

/**
 * Created by Ildar Gafarov on 09.12.15.
 */
public interface UnitRepository extends EntityRepository<Unit> {

    List<Unit> findByCourse(Course course);

}

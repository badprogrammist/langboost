package ru.langboost.domain.course.exercise;

import ru.langboost.domain.EnumType;

/**
 * Created by Ildar Gafarov on 12.12.15.
 */
public enum ExerciseType implements EnumType {

    TEXT_MATCHING("Соответсвие текста","tm");

    private String title;
    private String code;

    ExerciseType(String title,String code) {
        this.title = title;
        this.code = code;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public String getCode() {
        return code;
    }

    public static ExerciseType getByCode(String code) {
        for(ExerciseType type : values()) {
            if(type.getCode().equalsIgnoreCase(code)) {
                return type;
            }
        }
        return null;
    }
}

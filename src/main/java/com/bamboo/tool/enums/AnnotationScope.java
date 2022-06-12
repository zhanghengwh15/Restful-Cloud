package com.bamboo.tool.enums;

import lombok.Getter;

/**
 * Date 2022/2/16 13:07
 * Description
 * @author Administrator
 */
@Getter
public enum AnnotationScope {
    CLASS("Class"), METHOD("Method");

    private final String code;

    AnnotationScope(String code) {
        this.code = code;
    }

    public static AnnotationScope getAnnotationScope(String code) {
        for (AnnotationScope value : AnnotationScope.values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }
}

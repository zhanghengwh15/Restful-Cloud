package com.bamboo.tool.config.model;

import com.bamboo.tool.entity.AnnotationInfoSetting;
import com.intellij.psi.PsiClass;
import lombok.Data;

/**
 * Date 2022/1/27 10:22
 * Description
 * @author GuoQing
 */
@Data
public class PsiClassCache {

    private PsiClass psiClass;
    private AnnotationInfoSetting info;

    public PsiClassCache(AnnotationInfoSetting info, PsiClass psiClass) {
        this.psiClass = psiClass;
        this.info = info;
    }

}

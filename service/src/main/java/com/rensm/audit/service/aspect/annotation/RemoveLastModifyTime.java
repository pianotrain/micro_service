package com.rensm.audit.service.aspect.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Created by DELL on 2018/8/10.
 */
@Target(ElementType.METHOD)
public @interface RemoveLastModifyTime {
}

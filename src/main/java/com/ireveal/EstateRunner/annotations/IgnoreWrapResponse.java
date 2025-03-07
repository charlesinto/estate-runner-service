package com.ireveal.EstateRunner.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author CHARLES ONUORAH
 * @project com.ireveal.EstateRunner.annotations
 * @Date 03/02/2025
 */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreWrapResponse {
}

package com.teamconnect.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequireTeamPermission {
    TeamPermission[] value() default {};
    boolean requireAll() default false;
    boolean isSelfUserPermission() default false;
    boolean justTeamMember() default false;
}

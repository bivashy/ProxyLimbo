package com.ubivashka.limbo.nbt.resolver.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.ubivashka.limbo.nbt.type.TagDataType;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface TagData {
    String key() default "";

    TagDataType type();

    boolean isOptional() default false;

    /**
     * If protocol version will be greater or equal to this value, field will be resolved.
     */
    int minVersion() default 0;

    /**
     * If protocol version will be less or equal to this value, field will be resolved.
     */
    int maxVersion() default 759;

    String method() default "";
}
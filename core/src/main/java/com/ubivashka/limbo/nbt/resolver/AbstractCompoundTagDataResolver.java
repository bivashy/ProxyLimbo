package com.ubivashka.limbo.nbt.resolver;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.ubivashka.limbo.nbt.resolver.annotation.MultipleTagData;
import com.ubivashka.limbo.nbt.resolver.annotation.TagData;
import com.ubivashka.limbo.nbt.type.CompoundTag;
import com.ubivashka.limbo.util.StringUtil;

public abstract class AbstractCompoundTagDataResolver implements CompoundTagDataResolver<CompoundTag> {

    @Override
    public CompoundTag resolveTag(Object object, int protocolVersion) {
        try {
            List<Field> fields = deepFields(object.getClass()).stream()
                    .filter(field -> field.isAnnotationPresent(TagData.class) || field.isAnnotationPresent(MultipleTagData.class))
                    .collect(
                            Collectors.toList());
            CompoundTag compound = new CompoundTag();
            for (Field field : fields) {
                boolean isAccessible = field.isAccessible();
                field.setAccessible(true);

                if (field.isAnnotationPresent(TagData.class))
                    process(object, compound, field, protocolVersion, field.getAnnotation(TagData.class));
                if (field.isAnnotationPresent(MultipleTagData.class))
                    for (TagData tagData : field.getAnnotation(MultipleTagData.class).value())
                        process(object, compound, field, protocolVersion, tagData);

                field.setAccessible(isAccessible);
            }
            return compound;
        } catch(IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected void process(Object object, CompoundTag compound, Field field, int protocolVersion, TagData tagData) throws IllegalAccessException,
            NoSuchMethodException, InvocationTargetException {
        String key = tagData.key();

        if (tagData.minVersion() > protocolVersion || tagData.maxVersion() < protocolVersion)
            return;

        if (key.isEmpty())
            key = StringUtil.toSnakeCase(field.getName());

        Object fieldObject;
        if (tagData.method().isEmpty()) {
            fieldObject = field.get(object);
        } else {
            Method method = object.getClass().getDeclaredMethod(tagData.method(), int.class);
            boolean isAccessible = method.isAccessible();
            method.setAccessible(true);
            fieldObject = method.invoke(object, protocolVersion);
            method.setAccessible(isAccessible);
        }

        if (fieldObject == null) {
            if (!tagData.isOptional())
                throw new IllegalArgumentException("Tag in " + object.getClass().getSimpleName() + " with key " + key + " cannot be null!");
            return;
        }
        processField(fieldObject, new CompoundTagDataResolverContext(this, compound, tagData, field, key, protocolVersion));
    }

    protected abstract void processField(Object fieldObject, CompoundTagDataResolverContext context);

    private static Set<Field> deepFields(Class<?> currentClass) {
        Set<Field> fields = new HashSet<>();
        while(currentClass != null && currentClass != Object.class) {
            Collections.addAll(fields, currentClass.getDeclaredFields());
            currentClass = currentClass.getSuperclass();
        }
        return fields;
    }
}

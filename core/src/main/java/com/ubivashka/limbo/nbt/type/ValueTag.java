package com.ubivashka.limbo.nbt.type;

public abstract class ValueTag<T> implements Tag {
    private final T value;

    public ValueTag(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}

package com.ubivashka.limbo;

public class SingletonHolder<T> {
    private T object;

    public void setObject(T object) {
        if (this.object != null)
            throw new IllegalStateException("Cannot set object instance twice!");
        this.object = object;
    }

    public T getObject() {
        return object;
    }
}

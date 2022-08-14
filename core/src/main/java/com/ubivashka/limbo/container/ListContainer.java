package com.ubivashka.limbo.container;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListContainer<T> implements Container<T> {
    protected final List<T> list = new ArrayList<>();

    @Override
    public List<T> getCollection() {
        return Collections.unmodifiableList(list);
    }

    @Override
    public boolean add(T value) {
        if (value == null)
            return false;
        return list.add(value);
    }

    @Override
    public boolean remove(T value) {
        if (value == null)
            return false;
        return list.remove(value);
    }
}

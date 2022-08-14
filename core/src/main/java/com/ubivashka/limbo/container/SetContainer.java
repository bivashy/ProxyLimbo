package com.ubivashka.limbo.container;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SetContainer<T> implements Container<T> {
    private final Set<T> set = new HashSet<>();

    @Override
    public Collection<T> getCollection() {
        return Collections.unmodifiableSet(set);
    }

    @Override
    public boolean add(T value) {
        if (value == null)
            return false;
        return set.add(value);
    }

    @Override
    public boolean remove(T value) {
        if (value == null)
            return false;
        return set.remove(value);
    }
}

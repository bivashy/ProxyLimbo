package com.ubivashka.limbo.container;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public interface Container<T> {
    Collection<T> getCollection();

    boolean add(T value);

    boolean remove(T value);

    default Optional<T> findFirst(Predicate<T> filter) {
        return getCollection().stream().filter(filter).findFirst();
    }

    default <S> Stream<S> map(Function<T, S> function) {
        return getCollection().stream().map(function);
    }
}

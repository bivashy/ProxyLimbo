package com.ubivashka.limbo.nbt.resolver;

public interface CompoundTagDataResolver<T> {
    T resolveTag(Object object, int protocolVersion);
}

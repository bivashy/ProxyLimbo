package com.ubivashka.limbo.nbt.bungee.adapter;

import java.lang.reflect.InvocationTargetException;

import com.ubivashka.limbo.nbt.type.ByteArrayTag;
import com.ubivashka.limbo.nbt.type.Tag;

import se.llbit.nbt.SpecificTag;

public interface BungeeTagAdapter {
    Class<? extends BungeeTagAdapter>[] ADAPTERS = new Class[]{ByteTagAdapter.class, ShortTagAdapter.class, IntTagAdapter.class, LongTagAdapter.class, FloatTagAdapter.class,
            DoubleTagAdapter.class, ByteArrayTag.class, StringTagAdapter.class, ListTagAdapter.class, CompoundTagAdapter.class, IntArrayAdapter.class, LongArrayTagAdapter.class};

    static SpecificTag adapt(Tag tag) {
        try {
            return (SpecificTag) ADAPTERS[tag.getId() - 1].getConstructor(tag.getClass()).newInstance(tag);
        } catch(NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    static <T extends SpecificTag> T adaptCast(Tag tag) {
        return (T) adapt(tag);
    }
}

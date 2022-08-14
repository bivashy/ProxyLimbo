package com.ubivashka.limbo.nbt.bungee.adapter;

import se.llbit.nbt.FloatTag;

public class FloatTagAdapter extends FloatTag implements BungeeTagAdapter{
    public FloatTagAdapter(com.ubivashka.limbo.nbt.type.FloatTag tag) {
        super(tag.getValue());
    }
}

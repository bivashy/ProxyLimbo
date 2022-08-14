package com.ubivashka.limbo.nbt.bungee.adapter;

import se.llbit.nbt.DoubleTag;

public class DoubleTagAdapter extends DoubleTag implements BungeeTagAdapter{
    public DoubleTagAdapter(com.ubivashka.limbo.nbt.type.DoubleTag tag) {
        super(tag.getValue());
    }
}

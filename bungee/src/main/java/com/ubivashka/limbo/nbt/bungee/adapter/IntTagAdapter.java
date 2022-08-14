package com.ubivashka.limbo.nbt.bungee.adapter;

import se.llbit.nbt.IntTag;

public class IntTagAdapter extends IntTag implements BungeeTagAdapter{
    public IntTagAdapter(com.ubivashka.limbo.nbt.type.IntTag tag) {
        super(tag.getValue());
    }
}

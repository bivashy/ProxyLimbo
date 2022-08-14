package com.ubivashka.limbo.nbt.bungee.adapter;

import se.llbit.nbt.StringTag;

public class StringTagAdapter extends StringTag implements BungeeTagAdapter{
    public StringTagAdapter(com.ubivashka.limbo.nbt.type.StringTag tag) {
        super(tag.getValue());
    }
}

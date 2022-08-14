package com.ubivashka.limbo.nbt.bungee.adapter;

import se.llbit.nbt.ByteArrayTag;

public class ByteArrayTagAdapter extends ByteArrayTag implements BungeeTagAdapter{
    public ByteArrayTagAdapter(com.ubivashka.limbo.nbt.type.ByteArrayTag tag) {
        super(tag.getValue());
    }
}

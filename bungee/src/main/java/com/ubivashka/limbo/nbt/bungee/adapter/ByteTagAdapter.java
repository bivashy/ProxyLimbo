package com.ubivashka.limbo.nbt.bungee.adapter;

import se.llbit.nbt.ByteTag;

public class ByteTagAdapter extends ByteTag implements BungeeTagAdapter {
    public ByteTagAdapter(com.ubivashka.limbo.nbt.type.ByteTag tag) {
        super(tag.getValue());
    }
}

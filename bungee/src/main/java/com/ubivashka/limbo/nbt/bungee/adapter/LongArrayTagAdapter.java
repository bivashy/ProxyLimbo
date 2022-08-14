package com.ubivashka.limbo.nbt.bungee.adapter;

import se.llbit.nbt.LongArrayTag;

public class LongArrayTagAdapter extends LongArrayTag {
    public LongArrayTagAdapter(com.ubivashka.limbo.nbt.type.LongArrayTag tag) {
        super(tag.getValue());
    }
}

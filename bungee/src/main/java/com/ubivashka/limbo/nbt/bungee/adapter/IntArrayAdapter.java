package com.ubivashka.limbo.nbt.bungee.adapter;

import se.llbit.nbt.IntArrayTag;

public class IntArrayAdapter extends IntArrayTag {
    public IntArrayAdapter(com.ubivashka.limbo.nbt.type.IntArrayTag tag) {
        super(tag.getValue());
    }
}

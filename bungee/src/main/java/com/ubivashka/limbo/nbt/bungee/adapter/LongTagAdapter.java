package com.ubivashka.limbo.nbt.bungee.adapter;

import se.llbit.nbt.LongTag;

public class LongTagAdapter extends LongTag implements BungeeTagAdapter {
    public LongTagAdapter(com.ubivashka.limbo.nbt.type.LongTag tag) {
        super(tag.getValue());
    }
}

package com.ubivashka.limbo.nbt.bungee.adapter;

import se.llbit.nbt.ShortTag;

public class ShortTagAdapter extends ShortTag implements BungeeTagAdapter {
    public ShortTagAdapter(com.ubivashka.limbo.nbt.type.ShortTag tag) {
        super(tag.getValue());
    }
}

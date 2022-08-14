package com.ubivashka.limbo.nbt.bungee.adapter;

import java.util.Map.Entry;

import com.ubivashka.limbo.nbt.type.Tag;

import se.llbit.nbt.CompoundTag;

public class CompoundTagAdapter extends CompoundTag implements BungeeTagAdapter{
    public CompoundTagAdapter(com.ubivashka.limbo.nbt.type.CompoundTag tag){
        for(Entry<String, Tag> entry:tag.getTagMap().entrySet()) {
            add(entry.getKey(), BungeeTagAdapter.adapt(entry.getValue()));
        }
    }
}

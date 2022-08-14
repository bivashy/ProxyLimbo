package com.ubivashka.limbo.nbt.bungee.adapter;

import java.util.stream.Collectors;

import se.llbit.nbt.ListTag;

public class ListTagAdapter extends ListTag {
    /**
     * @param type  the type of tag that is stored in this list
     * @param items the items of this list
     */
    public ListTagAdapter(com.ubivashka.limbo.nbt.type.ListTag<?> listTag) {
        super(listTag.getListTypeId(), listTag.getTagCollection().stream().map(BungeeTagAdapter::adapt).collect(Collectors.toList()));
    }
}

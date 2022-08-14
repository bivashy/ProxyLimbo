package com.ubivashka.limbo.nbt.type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class ListTag<T extends Tag> implements Tag {
    public static final int ID = 9;
    private final Collection<T> tagList;
    private final Class<? extends T> listType;

    public ListTag(Class<? extends T> listType, Collection<T> tagCollection) {
        this.tagList = tagCollection;
        this.listType = listType;
    }

    public ListTag(Class<? extends T> listType, T... tags) {
        this(listType, Arrays.asList(tags));
    }

    public ListTag(Class<? extends T> listType) {
        this(listType, new ArrayList<>());
    }

    public void add(T object) {
        tagList.add(object);
    }

    public Collection<T> getTagCollection() {
        return Collections.unmodifiableCollection(tagList);
    }

    public Class<? extends T> getListType() {
        return listType;
    }

    public int getListTypeId() {
        try {
            return (int) getListType().getField("ID").get(null);
        } catch(NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int getId() {
        return ID;
    }
}

package com.ubivashka.limbo.nbt.type;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CompoundTag implements Tag {
    public static final int ID = 10;
    private final Map<String, Tag> tagMap = new HashMap<>();

    public void add(String key, Tag tag) {
        tagMap.put(key, tag);
    }

    public Map<String, Tag> getTagMap() {
        return Collections.unmodifiableMap(tagMap);
    }

    @Override
    public int getId() {
        return ID;
    }
}

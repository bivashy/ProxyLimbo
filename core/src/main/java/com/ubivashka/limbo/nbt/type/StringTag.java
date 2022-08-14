package com.ubivashka.limbo.nbt.type;

public class StringTag extends ValueTag<String> {
    public static final int ID = 8;
    public StringTag(String value) {
        super(value);
    }

    @Override
    public int getId() {
        return ID;
    }
}

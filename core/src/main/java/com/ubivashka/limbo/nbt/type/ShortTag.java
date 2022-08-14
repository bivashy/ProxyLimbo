package com.ubivashka.limbo.nbt.type;

public class ShortTag extends ValueTag<Short> {
    public static final int ID = 2;
    public ShortTag(short value) {
        super(value);
    }

    @Override
    public int getId() {
        return ID;
    }
}

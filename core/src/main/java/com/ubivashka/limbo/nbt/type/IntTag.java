package com.ubivashka.limbo.nbt.type;

public class IntTag extends ValueTag<Integer>{
    public static final int ID = 3;
    public IntTag(int value) {
        super(value);
    }

    @Override
    public int getId() {
        return ID;
    }
}

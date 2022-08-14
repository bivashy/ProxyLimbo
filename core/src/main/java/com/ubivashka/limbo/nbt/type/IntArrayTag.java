package com.ubivashka.limbo.nbt.type;

public class IntArrayTag extends ValueTag<int[]>{
    public static final int ID = 11;
    public IntArrayTag(int[] value) {
        super(value);
    }

    @Override
    public int getId() {
        return ID;
    }
}

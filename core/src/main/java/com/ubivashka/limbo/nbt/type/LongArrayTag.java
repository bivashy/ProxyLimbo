package com.ubivashka.limbo.nbt.type;

public class LongArrayTag extends ValueTag<long[]>{
    public static final int ID = 12;
    public LongArrayTag(long[] value) {
        super(value);
    }

    @Override
    public int getId() {
        return ID;
    }
}

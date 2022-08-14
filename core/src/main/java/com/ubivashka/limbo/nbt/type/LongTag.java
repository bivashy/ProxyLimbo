package com.ubivashka.limbo.nbt.type;

public class LongTag extends ValueTag<Long> {
    public static final int ID = 4;
    public LongTag(long value) {
        super(value);
    }

    @Override
    public int getId() {
        return ID;
    }
}

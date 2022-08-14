package com.ubivashka.limbo.nbt.type;

public class DoubleTag extends ValueTag<Double>{
    public static final int ID = 6;
    public DoubleTag(double value) {
        super(value);
    }

    @Override
    public int getId() {
        return ID;
    }
}

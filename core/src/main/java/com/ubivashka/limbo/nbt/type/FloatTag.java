package com.ubivashka.limbo.nbt.type;

public class FloatTag extends ValueTag<Float>{
    public static final int ID = 5;
    public FloatTag(float value) {
        super(value);
    }

    @Override
    public int getId() {
        return ID;
    }
}

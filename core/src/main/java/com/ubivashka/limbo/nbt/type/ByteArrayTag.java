package com.ubivashka.limbo.nbt.type;

public class ByteArrayTag extends ValueTag<byte[]>{
    public static final int ID = 7;
    public ByteArrayTag(byte[] value) {
        super(value);
    }

    @Override
    public int getId() {
        return ID;
    }
}

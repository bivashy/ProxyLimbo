package com.ubivashka.limbo.nbt.type;

public class ByteTag extends ValueTag<Byte> {
    public static final int ID = 1;

    public ByteTag(byte value) {
        super(value);
    }

    public ByteTag(boolean bool) {
        super((byte) (bool ? 1 : 0));
    }

    @Override
    public int getId() {
        return ID;
    }
}

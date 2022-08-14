package com.ubivashka.limbo.nbt.type;

public enum TagDataType {
    BYTE(ByteTag.class), SHORT(ShortTag.class), INT(IntTag.class), LONG(LongTag.class), FLOAT(FloatTag.class), DOUBLE(DoubleTag.class), BYTE_ARRAY(ByteArrayTag.class), STRING(StringTag.class), LIST(
            ListTag.class), COMPOUND(CompoundTag.class), INT_ARRAY(IntArrayTag.class), LONG_ARRAY(LongArrayTag.class);
    private final Class<? extends Tag> tagClass;

    TagDataType(Class<? extends Tag> tagClass) {
        this.tagClass = tagClass;
    }

    public Class<? extends Tag> getTagClass() {
        return tagClass;
    }

    public int getId() {
        return ordinal();
    }
}
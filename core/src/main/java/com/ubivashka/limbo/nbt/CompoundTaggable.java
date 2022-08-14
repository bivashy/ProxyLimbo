package com.ubivashka.limbo.nbt;

import com.ubivashka.limbo.ProxyLimbo;
import com.ubivashka.limbo.nbt.type.CompoundTag;

public interface CompoundTaggable {
    default CompoundTag asTag(int protocolVersion) {
        return ProxyLimbo.instance().getTagDataResolver().resolveTag(this,protocolVersion);
    }
}

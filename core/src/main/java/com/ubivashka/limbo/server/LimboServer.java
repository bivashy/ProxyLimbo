package com.ubivashka.limbo.server;

import com.ubivashka.limbo.function.Castable;
import com.ubivashka.limbo.player.LimboPlayer;
import com.ubivashka.limbo.protocol.nbt.registry.dimension.Dimension;
import com.ubivashka.limbo.protocol.nbt.registry.dimension.codec.DimensionCodec;

public interface LimboServer extends Castable<LimboServer> {
    void connect(LimboPlayer player);

    void disconnect(LimboPlayer player);

    DimensionCodec getDimensionCodec();

    Dimension getDimension();

    String getName();
}

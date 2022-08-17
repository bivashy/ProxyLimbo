package com.ubivashka.limbo.server;

import com.ubivashka.limbo.function.Castable;
import com.ubivashka.limbo.player.LimboPlayer;
import com.ubivashka.limbo.protocol.nbt.registry.dimension.Dimension;
import com.ubivashka.limbo.protocol.nbt.registry.dimension.DimensionRegistry.DimensionRegistryEntry;
import com.ubivashka.limbo.protocol.nbt.registry.dimension.codec.DimensionCodec;
import com.ubivashka.limbo.protocol.packet.UpdatePlayerPositionPacket;

public interface LimboServer extends Castable<LimboServer> {
    void connect(LimboPlayer player);

    void disconnect(LimboPlayer player);

    DimensionCodec getDimensionCodec();

    Dimension getDimension();

    String getName();

    interface LimboServerBuilder {
        LimboServerBuilder withDimensionCodec(DimensionCodec codec);

        LimboServerBuilder withDimensionType(Dimension.Type dimensionType);

        LimboServerBuilder withDimensionRegistryEntry(DimensionRegistryEntry dimensionRegistryEntry);

        LimboServerBuilder withPositionUpdatePacket(UpdatePlayerPositionPacket packet);

        LimboServerBuilder withName(String name);

        LimboServerBuilder withGamemode(short gamemode);

        LimboServerBuilder withBrand(String brand);

        LimboServer build();
    }
}

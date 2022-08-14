package com.ubivashka.limbo;


import java.util.Map;
import java.util.UUID;

import com.ubivashka.limbo.container.Container;
import com.ubivashka.limbo.function.Castable;
import com.ubivashka.limbo.nbt.resolver.CompoundTagDataResolver;
import com.ubivashka.limbo.nbt.type.CompoundTag;
import com.ubivashka.limbo.player.LimboPlayer;
import com.ubivashka.limbo.server.LimboServer;

public interface ProxyLimbo extends Castable<ProxyLimbo> {
    SingletonHolder<ProxyLimbo> LIMBO_SINGLETON_HOLDER = new SingletonHolder<>();

    static ProxyLimbo instance() {
        return LIMBO_SINGLETON_HOLDER.getObject();
    }

    CompoundTagDataResolver<CompoundTag> getTagDataResolver();

    Container<LimboServer> getLimboServerContainer();

    Map<UUID,LimboPlayer> getLimboPlayers();

    LimboPlayer findLimboPlayer(UUID uniqueId);

    LimboPlayer fetchLimboPlayer(Object object);
}

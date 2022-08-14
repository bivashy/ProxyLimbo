package com.ubivashka.limbo.bungee.server;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.ubivashka.limbo.bungee.config.limbo.LimboSettings;
import com.ubivashka.limbo.bungee.packet.LoginPacketBuilder;
import com.ubivashka.limbo.bungee.packet.RespawnPacketBuilder;
import com.ubivashka.limbo.bungee.player.BungeeLimboPlayer;
import com.ubivashka.limbo.nbt.bungee.adapter.BungeeTagAdapter;
import com.ubivashka.limbo.player.LimboPlayer;
import com.ubivashka.limbo.protocol.nbt.registry.dimension.Dimension;
import com.ubivashka.limbo.protocol.nbt.registry.dimension.DimensionRegistry.DimensionRegistryEntry;
import com.ubivashka.limbo.protocol.nbt.registry.dimension.codec.DimensionCodec;
import com.ubivashka.limbo.server.LimboServer;

import dev.simplix.protocolize.api.util.ProtocolUtil;
import dev.simplix.protocolize.api.util.ProtocolVersions;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import net.md_5.bungee.BungeeServerInfo;
import net.md_5.bungee.ServerConnection;
import net.md_5.bungee.UserConnection;
import net.md_5.bungee.api.Callback;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.netty.ChannelWrapper;
import net.md_5.bungee.protocol.packet.GameState;
import net.md_5.bungee.protocol.packet.PluginMessage;
import se.llbit.nbt.NamedTag;

public class BungeeLimboServer extends ServerConnection implements LimboServer {
    private final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(10);
    private final Map<Integer, NamedTag> dimensionCodecNBTMap = new HashMap<>();
    private final Map<Integer, NamedTag> dimensionEntryNBTMap = new HashMap<>();
    private final DimensionCodec dimensionCodec;
    private final DimensionRegistryEntry dimensionEntry;
    private final LimboSettings limboSettings;

    public BungeeLimboServer(LimboSettings limboSettings) {
        super(new ChannelWrapper(new ChannelHandlerContextWrapper(new EmbeddedChannel())), new BungeeLimboServerInfo(limboSettings.getName()));
        this.dimensionCodec = limboSettings.getDimensionCodec();
        this.dimensionEntry = limboSettings.getDimensionSettings().getDimensionRegistryEntry();
        this.limboSettings = limboSettings;
    }

    @Override
    public void connect(LimboPlayer player) {
        player.setConnecting(true);
        player.setCurrentLimbo(this);

        UserConnection userConnection = (UserConnection) player.as(BungeeLimboPlayer.class).getPlayer();
        getInfo().addPlayer(userConnection);

        if (userConnection.getServer() != null) {
            userConnection.getServer().setObsolete(true);
            userConnection.getServer().getCh().close();
        }
        userConnection.setServer(this);
        EXECUTOR_SERVICE.execute(() -> {
            int entityId = player.getEntityId();
            int protocolVersion = player.getProtocolVersion();


            NamedTag dimensionCodecTag = dimensionCodecNBTMap.computeIfAbsent(protocolVersion,
                    key -> new NamedTag("", BungeeTagAdapter.adapt(dimensionCodec.asTag(protocolVersion))));
            NamedTag dimensionTag = dimensionEntryNBTMap.computeIfAbsent(protocolVersion,
                    key -> new NamedTag("", BungeeTagAdapter.adapt(dimensionEntry.getElement().asTag(protocolVersion))));
            String dimensionName = dimensionEntry.getName();

            Object dimensionObject = dimensionTag;
            if (protocolVersion >= ProtocolVersions.MINECRAFT_1_19)
                dimensionObject = dimensionName;
            if (protocolVersion < ProtocolVersions.MINECRAFT_1_16)
                dimensionObject = limboSettings.getDimensionSettings().getType().getDimensionId();
            short gamemode = (short) limboSettings.getGamemode();
            player.sendPacket(new LoginPacketBuilder().withEntityId(entityId)
                    .withGamemode(gamemode)
                    .withDimensionName(dimensionName)
                    .withDimensionNames(Collections.singleton(dimensionName))
                    .withDimensionCodec(dimensionCodecTag)
                    .withDimension(dimensionObject)
                    .build());
            if (protocolVersion >= ProtocolVersions.MINECRAFT_1_15)
                player.sendPacket(new GameState(GameState.IMMEDIATE_RESPAWN, 1));
            player.sendPacket(new RespawnPacketBuilder().withDimension(dimensionObject).withDimensionName(dimensionName).withGamemode(gamemode).build());
            player.sendPacket(limboSettings.getCoordinateSettings().getPlayerPositionPacket());

            ByteBuf byteBuf = Unpooled.buffer();
            ProtocolUtil.writeString(byteBuf, limboSettings.getBrand());
            player.sendPacket(new PluginMessage("minecraft:brand", byteBuf.array(), false));
        });
    }

    @Override
    public void disconnect(LimboPlayer player) {
        getInfo().removePlayer(player.as(BungeeLimboPlayer.class).getPlayer());
    }

    @Override
    public DimensionCodec getDimensionCodec() {
        return dimensionCodec;
    }

    @Override
    public Dimension getDimension() {
        return dimensionEntry.getElement();
    }

    @Override
    public String getName() {
        return getInfo().getName();
    }

    @Override
    public boolean isConnected() {
        return true;
    }

    public static class BungeeLimboServerInfo extends BungeeServerInfo {
        public BungeeLimboServerInfo(String name) {
            super(name, new InetSocketAddress("0.0.0.0", 0), "", false);
        }

        @Override
        public Collection<ProxiedPlayer> getPlayers() {
            return Collections.emptyList();
        }

        @Override
        public void ping(Callback<ServerPing> callback) {
        }

        @Override
        public void ping(Callback<ServerPing> callback, int protocolVersion) {
        }
    }
}

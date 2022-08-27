package com.ubivashka.limbo.bungee.config.limbo;

import com.ubivashka.configuration.ConfigurationHolder;
import com.ubivashka.configuration.annotation.ConfigField;
import com.ubivashka.configuration.holder.ConfigurationSectionHolder;
import com.ubivashka.limbo.bungee.config.PluginConfig;
import com.ubivashka.limbo.protocol.packet.UpdatePlayerPositionPacket;

public class CoordinateSettings implements ConfigurationHolder {
    @ConfigField
    private double x, y, z;
    @ConfigField
    private float yaw, pitch;

    private final UpdatePlayerPositionPacket playerPositionPacket;

    public CoordinateSettings(ConfigurationSectionHolder sectionHolder) {
        PluginConfig.CONFIGURATION_PROCESSOR.resolve(sectionHolder, this);
        this.playerPositionPacket = new UpdatePlayerPositionPacket(x, y, z, yaw, pitch, (byte) 0, 0, false);
    }

    public CoordinateSettings(double x, double y, double z, float yaw, float pitch) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.playerPositionPacket = new UpdatePlayerPositionPacket(x, y, z, yaw, pitch, (byte) 0, 0, false);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public UpdatePlayerPositionPacket getPlayerPositionPacket() {
        return playerPositionPacket;
    }
}

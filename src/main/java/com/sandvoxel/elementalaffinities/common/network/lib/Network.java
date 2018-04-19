package com.sandvoxel.elementalaffinities.common.network.lib;

import com.sandvoxel.elementalaffinities.Reference;
import com.sandvoxel.elementalaffinities.common.network.AffinityGuiPacket;
import com.sandvoxel.elementalaffinities.common.network.AffinityPacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class Network extends NetworkWrapperBase {
    private static Network instance = new Network();

    public Network() {
        super(Reference.MOD_ID);
    }

    public static Network getInstance() {
        return instance;
    }

    public static void init() {
        getInstance().registerPacket(AffinityGuiPacket.class);
        getInstance().registerPacket(AffinityPacket.class);
    }

    public static void sendPacket(Entity player, Packet<?> packet) {
        if (player instanceof EntityPlayerMP && ((EntityPlayerMP) player).connection != null) {
            ((EntityPlayerMP) player).connection.sendPacket(packet);
        }
    }

    public static void sendToAll(PacketBase packet) {
        getInstance().network.sendToAll(packet);
    }

    public static void sendTo(PacketBase packet, EntityPlayerMP player) {
        getInstance().network.sendTo(packet, player);
    }

    public static void sendToAllAround(PacketBase packet, NetworkRegistry.TargetPoint point) {
        getInstance().network.sendToAllAround(packet, point);
    }

    public static void sendToDimension(PacketBase packet, int dimensionId) {
        getInstance().network.sendToDimension(packet, dimensionId);
    }

    public static void sendToServer(PacketBase packet) {
        getInstance().network.sendToServer(packet);
    }

    public static void sendToClients(WorldServer worldServer, BlockPos blockPos, PacketBase packet) {
        Chunk chunk = worldServer.getChunkFromBlockCoords(blockPos);
        for (EntityPlayer player : worldServer.playerEntities) {
            if (!(player instanceof EntityPlayerMP))
                continue;

            EntityPlayerMP playerMP = (EntityPlayerMP) player;
            if (worldServer.getPlayerChunkMap().isPlayerWatchingChunk(playerMP, chunk.x, chunk.z)) {
                Network.sendTo(packet, playerMP);
            }
        }
    }
}

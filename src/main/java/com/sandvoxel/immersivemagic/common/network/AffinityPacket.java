package com.sandvoxel.immersivemagic.common.network;

import com.sandvoxel.immersivemagic.ImmersiveMagic;
import com.sandvoxel.immersivemagic.api.magic.IAffinities;
import com.sandvoxel.immersivemagic.common.magicdata.AffinitiesProvider;
import com.sandvoxel.immersivemagic.common.magicdata.AffinityTypes;
import com.sandvoxel.immersivemagic.common.network.lib.PacketBase;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


/**
 * Created by CrazyGrape on 4/3/2018.
 */
public class AffinityPacket extends PacketBase {

    private boolean[] hasAffinityArr = new boolean[8];
    private int entityID;
    private EntityPlayer player;

    public AffinityPacket() {
        //ImmersiveMagic.LOGGER.info("Initialized without any parameters!");
        setPlayerSP();
    }

    public AffinityPacket(IAffinities playerAffinities, EntityPlayerMP player) {
        //ImmersiveMagic.LOGGER.info("Initialized with parameters!");
        for (AffinityTypes type : AffinityTypes.values()){
            hasAffinityArr[type.getMeta()] = playerAffinities.hasAffinity(type);
        }
        this.player = player;
    }

    @SideOnly(Side.CLIENT)
    public void setPlayerSP() {
        player = Minecraft.getMinecraft().player;
    }

    @Override
    public IMessage handleClient(NetHandlerPlayClient netHandler) {
        ImmersiveMagic.LOGGER.info(hasAffinityArr);

        for (AffinityTypes type : AffinityTypes.values()){
            if (hasAffinityArr[type.getMeta()]) {
                player.getCapability(AffinitiesProvider.AFFINITIES_CAPABILITY, null).addAffinities(type);
            } else {
                player.getCapability(AffinitiesProvider.AFFINITIES_CAPABILITY, null).removeAffinity(type);
            }
        }

        /*for(AffinityTypes type : AffinityTypes.values()){
            ImmersiveMagic.LOGGER.info(type.getName() + ":. " + player.getCapability(AffinitiesProvider.AFFINITIES_CAPABILITY, null).hasAffinity(type));
        }*/

        return null;
    }

    @Override
    public IMessage handleServer(NetHandlerPlayServer netHandler) {
        return null;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        entityID = ByteBufUtils.readVarInt(buf, 4);
        for (int i = 0; i < hasAffinityArr.length; ++i) {
            ImmersiveMagic.LOGGER.info(i);
            hasAffinityArr[i] = buf.readBoolean();
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeVarInt(buf, player.getEntityId(), 4);
        for (boolean bool : hasAffinityArr){
            buf.writeBoolean(bool);
        }
    }
}

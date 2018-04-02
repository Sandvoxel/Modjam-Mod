package com.sandvoxel.immersivemagic.common.network;

import com.jcraft.jogg.Packet;
import com.sandvoxel.immersivemagic.ImmersiveMagic;
import com.sandvoxel.immersivemagic.api.magic.IAffinities;
import com.sandvoxel.immersivemagic.common.magicdata.AffinitiesProvider;
import com.sandvoxel.immersivemagic.common.magicdata.AffinityObject;
import com.sandvoxel.immersivemagic.common.magicdata.AffinityTypes;
import com.sandvoxel.immersivemagic.common.network.lib.PacketBase;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.UUID;

public class AffinityGuiPacket extends PacketBase {

    private int id;
    private int power;
    private int mana;

    public AffinityGuiPacket() {
    }

    public AffinityGuiPacket(int id, int power) {
        this.id = id;
        this.power = power;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.id = buf.readInt();
        this.power = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(id);
        buf.writeInt(power);
    }


    @Override
    public IMessage handleClient(NetHandlerPlayClient netHandler) {
        return null;
    }

    @Override
    public IMessage handleServer(NetHandlerPlayServer netHandler) {
        IAffinities affinities = netHandler.player.getCapability(AffinitiesProvider.AFFINITIES_CAPABILITY,null);

        AffinityTypes affinityType = AffinityTypes.getAffinity(id);

        if(affinities.hasAffinity(affinityType)){
            affinities.removeAffinity(affinityType);
        }else {
            affinities.addAffinities(affinityType);
        }
        affinities.addXp(100,AffinityTypes.FIRE);
        return null;
    }
}

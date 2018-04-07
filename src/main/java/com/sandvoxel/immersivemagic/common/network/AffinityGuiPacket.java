package com.sandvoxel.immersivemagic.common.network;

import com.sandvoxel.immersivemagic.ImmersiveMagic;
import com.sandvoxel.immersivemagic.api.magic.IAffinities;
import com.sandvoxel.immersivemagic.common.magicdata.AffinitiesProvider;
import com.sandvoxel.immersivemagic.common.magicdata.AffinityTypes;
import com.sandvoxel.immersivemagic.common.network.lib.Network;
import com.sandvoxel.immersivemagic.common.network.lib.PacketBase;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class AffinityGuiPacket extends PacketBase {

    private int id;
    private int power;
    private int mana;
    private boolean active;
    private int affinityCap = 3;

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

        if (affinities.hasAffinity(affinityType)){
            affinities.removeAffinity(affinityType);
            ImmersiveMagic.LOGGER.info("Removed affinity '" + affinityType.getName() + "' from player " + netHandler.player.getName());
        } else if (!affinities.hasAffinity(affinityType)) {
            int activeNum = 0;
            for(AffinityTypes type : AffinityTypes.values()){
                if(affinities.hasAffinity(type)){
                    activeNum++;
                }
            }
            if (activeNum >= affinityCap) {
                ImmersiveMagic.LOGGER.info("Player " + netHandler.player.getName() + " already has " + activeNum + " affinities. Cannot add " + affinityType.getName() + "!");
            } else {
                affinities.addAffinities(affinityType);
                ImmersiveMagic.LOGGER.info("Added affinity '" + affinityType.getName() + "' to player " + netHandler.player.getName());
            }
        } else {
            ImmersiveMagic.LOGGER.info("Mis-match between affinity status request and actual affinity status. Server is out of sync with " + netHandler.player.getName() + " for affinity '" + affinityType.getName() + "'!" );
        }
        //ImmersiveMagic.LOGGER.info("About to send AffinityPacket ");
        for(AffinityTypes type : AffinityTypes.values()){
            ImmersiveMagic.LOGGER.info(type.getName() + ": " + affinities.hasAffinity(type));
        }
        Network.sendTo(new AffinityPacket(affinities, netHandler.player), netHandler.player);

        //affinities.addXp(100, affinityType);
        return null;
    }

}

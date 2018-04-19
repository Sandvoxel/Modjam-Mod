package com.sandvoxel.elementalaffinities.common.network;

import com.sandvoxel.elementalaffinities.ElementalAffinities;
import com.sandvoxel.elementalaffinities.api.magic.IAffinities;
import com.sandvoxel.elementalaffinities.common.magicdata.AffinitiesProvider;
import com.sandvoxel.elementalaffinities.common.magicdata.AffinityTypes;
import com.sandvoxel.elementalaffinities.common.network.lib.Network;
import com.sandvoxel.elementalaffinities.common.network.lib.PacketBase;
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
    private boolean onlyRequesting = false;

    public AffinityGuiPacket() {
    }

    public AffinityGuiPacket(int id, int power, boolean onlyRequestingInfo) {
        this.id = id;
        this.power = power;
        onlyRequesting = onlyRequestingInfo;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.id = buf.readInt();
        this.power = buf.readInt();
        this.onlyRequesting = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(id);
        buf.writeInt(power);
        buf.writeBoolean(onlyRequesting);
    }


    @Override
    public IMessage handleClient(NetHandlerPlayClient netHandler) {
        return null;
    }



    @Override
    public IMessage handleServer(NetHandlerPlayServer netHandler) {
        IAffinities affinities = netHandler.player.getCapability(AffinitiesProvider.AFFINITIES_CAPABILITY,null);

        if (!onlyRequesting) {
            AffinityTypes affinityType = AffinityTypes.getAffinity(id);
            if (affinities.hasAffinity(affinityType)) {
                affinities.removeAffinity(affinityType);
                ElementalAffinities.LOGGER.info("Removed affinity '" + affinityType.getName() + "' from player " + netHandler.player.getName());
            } else if (!affinities.hasAffinity(affinityType)) {
                int activeNum = 0;
                for (AffinityTypes type : AffinityTypes.values()) {
                    if (affinities.hasAffinity(type)) {
                        activeNum++;
                    }
                }
                if (activeNum >= affinityCap) {
                    ElementalAffinities.LOGGER.info("Player " + netHandler.player.getName() + " already has " + activeNum + " affinities. Cannot add " + affinityType.getName() + "!");
                } else {
                    affinities.addAffinities(affinityType);
                    ElementalAffinities.LOGGER.info("Added affinity '" + affinityType.getName() + "' to player " + netHandler.player.getName());
                }
            } else {
                ElementalAffinities.LOGGER.info("Mis-match between affinity status request and actual affinity status. Server is out of sync with " + netHandler.player.getName() + " for affinity '" + affinityType.getName() + "'!");
            }
            //ElementalAffinities.LOGGER.info("About to send AffinityPacket ");
            /*for (AffinityTypes type : AffinityTypes.values()) {
                ElementalAffinities.LOGGER.info(type.getName() + ": " + affinities.hasAffinity(type));
            }*/
        }
        Network.sendTo(new AffinityPacket(affinities, netHandler.player), netHandler.player);

        //affinities.addXp(100, affinityType);
        return null;
    }

}

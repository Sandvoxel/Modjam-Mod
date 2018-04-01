package com.sandvoxel.immersivemagic.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class AffinityGuiPacket implements IMessage, IMessageHandler<AffinityGuiPacket, IMessage> {


    public AffinityGuiPacket() {
    }

    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {

    }

    @Override
    public IMessage onMessage(AffinityGuiPacket message, MessageContext ctx) {
        return null;
    }
}

package com.game.worm.service.udp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UdpServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
        log.info("msg");
        ByteBuf b = msg.content();
        Byte bd;
        StringBuilder aa = new StringBuilder();
        byte[] bytes = new byte[b.maxCapacity()];
        while(b.isReadable()){
            bd = b.readByte();
            aa.append(bd.toString());
        }


    }
}

package com.ultra.netty.tcp.codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

public class HexEncoder extends MessageToMessageEncoder {

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, List out) throws Exception {

    }
}

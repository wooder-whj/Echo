package net.netty.websocket.echo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;

public class EchoChannelHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    Logger logger=LoggerFactory.getLogger(EchoChannelHandler.class);
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        logger.info("From web socket client ["+ctx.channel().remoteAddress().toString()+"] message: "+msg.text());
        ctx.channel().writeAndFlush(new TextWebSocketFrame(LocalTime.now()+": "+msg.text()));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        super.exceptionCaught(ctx, cause);
        logger.error("异常发生！"+cause.getMessage());
        ctx.close();
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
//        super.handlerAdded(ctx);
        logger.info("handlerAdded: "+ctx.channel().id().asLongText());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
//        super.handlerRemoved(ctx);
        logger.info("handlerRemoved: "+ctx.channel().id().asLongText());
    }
}

package pers.guo.repositorytemplate.sysio.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * 手动实现netty 代码
 *
 * 读事件的处理方法
 * @author abner
 * @version 1.0
 * @description: TODO
 * @date 2023/7/10 23:18
 */
public class MyClientInHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client  registed...");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client active...");
    }

    /***
     * 读取 通信中的数据
     * @author abner
     * @date: 2023/7/10 23:21
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        /**
         * CharSequence str = buf.readCharSequence(buf.readableBytes(), CharsetUtil.UTF_8);
         * 读也可以使用readCharSequence，read 会进行指针移动
         * 方便回写，使用getCharSequence 方法
         */
        CharSequence str = buf.getCharSequence(0, buf.readableBytes(), CharsetUtil.UTF_8);
        System.out.println(str);
    }


}

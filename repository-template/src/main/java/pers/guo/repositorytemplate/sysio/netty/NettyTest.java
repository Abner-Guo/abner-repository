package pers.guo.repositorytemplate.sysio.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.junit.Test;

import java.net.InetSocketAddress;

/**
 * 使用netty 框架实现 {@link pers.guo.repositorytemplate.sysio.netty.MyNetty MyNetty} 功能
 *
 * @author abner
 * @version 1.0
 * @date 2023/7/10 22:43
 */
public class NettyTest {

    @Test
    public void nettyClient() throws InterruptedException {

        NioEventLoopGroup group = new NioEventLoopGroup(1);

        Bootstrap bootstrap = new Bootstrap();

        ChannelFuture connect = bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast(new MyClientInHandler());
                    }
                })
                .connect(new InetSocketAddress("10.2.111.169", 9999));

        //同步阻塞
        Channel channel = connect.sync().channel();

        ByteBuf byteBuf = Unpooled.copiedBuffer("客户端发送..".getBytes());

        ChannelFuture seed = channel.writeAndFlush(byteBuf);

        seed.sync();

        connect.channel().closeFuture().sync();

    }

    @Test
    public void nettyServer() throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup(1);
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        ChannelFuture bind = serverBootstrap.group(group, group)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        ChannelPipeline pipeline = nioSocketChannel.pipeline();
                        pipeline.addLast(new MyInHandler());
                    }
                })
                .bind(new InetSocketAddress("10.2.111.169", 9999));

        bind.sync().channel().closeFuture().sync();


    }
}

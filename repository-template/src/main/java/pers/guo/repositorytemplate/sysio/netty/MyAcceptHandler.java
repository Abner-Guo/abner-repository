package pers.guo.repositorytemplate.sysio.netty;

import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.concurrent.EventExecutorGroup;

/**
 * 手动实现netty 代码
 *
 * 接受连接事件的处理方法
 *
 * @author abner
 * @version 1.0
 * @description: TODO
 * @date 2023/7/10 23:40
 */
public class MyAcceptHandler extends ChannelInboundHandlerAdapter {

    private final EventLoopGroup selector;
    private final ChannelHandler handler;

    public MyAcceptHandler(EventLoopGroup thread, ChannelHandler myInHandler) {
        this.selector = thread;
        this.handler = myInHandler;  //ChannelInit
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("server registerd...");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        /**
         * 连接事件：listen  socket   accept    client
         * 通信事件：socket           R/W
         *
         * 服务端读取到的是客户端
         * 客户端 响应式加入读处理事件
         *
         * selector.register(client); 客户端连接注册 epoll_ctl(5 add 3)
         *
         */
        SocketChannel client = (SocketChannel) msg;  //accept  我怎么没调用额？
        //2，响应式的  handler
        ChannelPipeline p = client.pipeline();
        p.addLast(handler);  //1,client::pipeline[ChannelInit,]

        //1，注册
        selector.register(client);
    }

}

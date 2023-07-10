package pers.guo.repositorytemplate.sysio.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.junit.Test;

import java.net.InetSocketAddress;

/**
 * 手动实现netty 代码
 * 按照epoll 的思想，按照{@link pers.guo.repositorytemplate.sysio.io.SocketMultiplexingSingleThreadv1 SocketMultiplexingSingleThreadv1}
 * 自己实现netty
 *
 * <p>依托着nio的思维逻辑</p>
 *
 * @author abner
 * @version 1.0
 * @date 2023/7/10 22:43
 */
public class MyNetty {

    /***
     * @description: Bytebuf 对应 jdk 的 bytebuffer
     * 基于缓冲的读写，IO 读写使用的中间缓冲区
     * @author abner
     * @date: 2023/7/10 22:51
     */
    @Test
    public void myBytebuf(){

        /**
         * 默认是堆外
         * ByteBuf buf = ByteBufAllocator.DEFAULT.buffer(8, 20);
         *
         * 非池化
         * ByteBuf buf = UnpooledByteBufAllocator.DEFAULT.heapBuffer(8, 20);
         */
        ByteBuf buf = PooledByteBufAllocator.DEFAULT.heapBuffer(4, 20);
        print(buf);

        buf.writeBytes(new byte[]{1,2,3,4});
        print(buf);
        buf.writeBytes(new byte[]{1,2,3,4});
        print(buf);
        buf.writeBytes(new byte[]{1,2,3,4});
        print(buf);
        buf.writeBytes(new byte[]{1,2,3,4});
        print(buf);
        buf.writeBytes(new byte[]{1,2,3,4});
        print(buf);
        buf.writeBytes(new byte[]{1,2,3,4});
        print(buf);




    }

    public static void print(ByteBuf buf){
        System.out.println("buf.isReadable()    :"+buf.isReadable());
        System.out.println("buf.readerIndex()   :"+buf.readerIndex());
        System.out.println("buf.readableBytes() "+buf.readableBytes());
        System.out.println("buf.isWritable()    :"+buf.isWritable());
        System.out.println("buf.writerIndex()   :"+buf.writerIndex());
        System.out.println("buf.writableBytes() :"+buf.writableBytes());
        System.out.println("实际长度 buf.capacity()  :"+buf.capacity());
        System.out.println("最大长度 buf.maxCapacity()   :"+buf.maxCapacity());
        System.out.println("是否是堆外 buf.isDirect()  :"+buf.isDirect());
        System.out.println("--------------");
    }

    /**
     * netty 中使用的线程池
     * @throws Exception
     */
    @Test
    public void loopExecutor() throws Exception {
        NioEventLoopGroup selector = new NioEventLoopGroup(2);
        selector.execute(()->{
            try {
                for (;;){
                    System.out.println("hello world001");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        selector.execute(()->{
            try {
                for (;;){
                    System.out.println("hello world002");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.in.read();
    }


    /***
     * 客户端
     * 1.主动发送数据给服务端
     * 2.随时能接收到服务端的数据 event select 响应式
     *
     * <p>启动服务端`nc -l 192.168.1.29 9090`<p/>
     * @author abner
     * @date: 2023/7/10 22:59
     */
    @Test
    public void clientMode() throws Exception {
        // 一个线程,一个selector

        /**
         * NioEventLoopGroup 相当于 一个线程,一个selector
         *
         * register：epoll_ctl(5,ADD,3)
         */
        NioEventLoopGroup group = new NioEventLoopGroup(1);

        NioSocketChannel channel = new NioSocketChannel();

        group.register(channel);

        /**
         * 随时能接收到服务端的数据 event select 响应式
         * ChannelPipeline:通道，事件的处理方法存放的位置，方便当有事件发生时调起
         * MyInHandler:读事件的处理方法
         */
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast(new MyInHandler());


        /**
         * 主动发送数据
         *
         * (connect)连接，(writeAndFlush)通信都是异步的
         * 可以理解为 不同事件在不同线程中操作
         */
        ChannelFuture connect = channel.connect(new InetSocketAddress("192.168.1.29", 9090));

        ChannelFuture sync = connect.sync();

        ByteBuf buf = Unpooled.copiedBuffer("客户端发送..".getBytes());

        ChannelFuture seed = channel.writeAndFlush(buf);

        seed.sync();

        sync.channel().closeFuture().sync();
    }


    /***
     * 服务端
     * 1.随时能接收客户端连接，客户端的通信读
     * 2.能主动写数据到客户端
     *
     * <p>启动客户端`nc 192.168.1.29 9090`<p/>
     * @author abner
     * @date: 2023/7/10 23:34
     */
    @Test
    public void serverMode() throws Exception {

        NioEventLoopGroup group = new NioEventLoopGroup();

        NioServerSocketChannel serverSocketChannel = new NioServerSocketChannel();

        group.register(serverSocketChannel);

        /**
         * 随时能接收客户端连接，客户端的通信读 (响应式)
         *
         * accept接收客户端，并且注册到selector
         *
         * 这里MyAcceptHandler，ChannelInit 都是单例的
         * 为保证多个客户端连接，有多个读操作处理实现多例
         * 所以使用ChannelInit 作为桥梁，在内部实现多例
         *
         */
        ChannelPipeline pipeline = serverSocketChannel.pipeline();
        pipeline.addLast(new MyAcceptHandler(group,new ChannelInit()));

        ChannelFuture bind = serverSocketChannel.bind(new InetSocketAddress("192.168.1.29", 9090));

        bind.sync();

        bind.sync().channel().closeFuture().sync();
        System.out.println("server close....");

    }




}

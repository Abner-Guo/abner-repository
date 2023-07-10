 package pers.guo.repositorytemplate.sysio.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/***
 *
 * 多路复用器单线程测试demo
 *
 * <p>启动默认使用epoll,如果要使用poll 要加JVM   参数 -Djava.nio.channels.spi.SelectorProvider=sun.nio.ch.PollSelectorProvider</p>
 *
 * @author abner
 * @date: 2023/7/8 14:47
 */
public class SocketMultiplexingSingleThreadv1 {

    private ServerSocketChannel server = null;
    /**
     * linux 多路复用器（select poll    epoll kqueue） nginx  event{}
     */
    private Selector selector = null;
    int port = 9090;

    public void initServer() {
        try {
            /**
             * server 约等于 listen状态的 fd4
             */
            server = ServerSocketChannel.open();
            server.configureBlocking(false);
            server.bind(new InetSocketAddress(port));


            /**
             * 创建多路复用器
             * select  poll  *epoll  优先选择：epoll  但是可以 -D修正
             * 如果在epoll模型下，open--》  epoll_create -> fd3
             */
            selector = Selector.open();


            /**
             * select，poll：jvm里开辟一个数组， fd4 放进去
             * epoll：  epoll_ctl(fd3,ADD,fd4,EPOLLIN
             *
             * SelectionKey.OP_ACCEPT fd4关注连接的事件,等着别人来连接
             */
            server.register(selector, SelectionKey.OP_ACCEPT);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        initServer();
        System.out.println("服务器启动了。。。。。");
        try {
            while (true) {  //死循环

                Set<SelectionKey> keys = selector.keys();
                System.out.println(keys.size()+"   size");

                /**
                 * select() 方法
                 * 1，select，poll：其实是内核调用 select（fd4...）  poll(fd4...) 将文件描述符传入系统内核
                 * 2，epoll：其实是内核的 epoll_wait()
                 *      epoll_wait()含义是：多路复用器event-poll中有连接事件，或者通信事件，返回这两种事件的个数
                 *      连接事件：通过网卡，客户端连接了listen状态的fd4
                 *      通信事件：通过网卡，给某个已经建立连接的fd，发送了数据包
                 *
                 * 参数可以带时间：没有时间，0：阻塞，有时间设置一个超时
                 *
                 * 懒加载：
                 * 其实再触碰到selector.select()调用的时候触发了epoll_ctl的调用
                 * selector.wakeup()  结果返回0 
                 *
                 */
                while (selector.select() > 0) {
                    /**
                     * selectedKeys()  返回的有状态的fd集合
                     *
                     * 有状态的fd分为两种：发生了连接事件，发生了通信事件
                     *
                     * 不管什么多路复用器，只能返回状态，程序还得一个一个的去处理他们的R/W 【验证多路复用器是同步IO模型】
                     *
                     */
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iter = selectionKeys.iterator();
                    while (iter.hasNext()) {
                        SelectionKey key = iter.next();
                        iter.remove(); //不移除会重复循环处理
                        if (key.isAcceptable()) {
                            /**
                             * 发生了连接事件
                             *
                             * 语义上，accept接受连接且返回新连接的FD
                             * 1.select，poll：因为他们内核没有空间，那么在jvm中保存和前边的fd4那个listen的一起
                             * 2.epoll：我们希望通过epoll_ctl把新的客户端fd注册到内核空间
                             *
                             */
                            acceptHandler(key);
                        } else if (key.isReadable()) {
                            /**
                             * 发生了通信事件
                             * 读数据包
                             * 在当前线程，这个方法可能会阻塞 ，如果阻塞了很久，其他的IO就会超时
                             *
                             * 所以，为什么提出了 IO THREADS
                             * redis 中使用了epoll，redis就有个io threads的概念 ，redis是不是单线程的
                             * 所以在回答redis是不是单线程的？
                             * redis 在处理redis指令的时候是单线程的
                             * 但是在发生io的时候是多线程的
                             *
                             * tomcat 8,9  异步的处理方式  IO  和   处理上  解耦
                             *
                             */
                            readHandler(key);  //连read 还有 write都处理了
                        }else if(key.isWritable()){
                            /**
                             * 程序什么时候写？不是依赖send-queue是不是有空间（多路复用器能不能写是参考send-queue有没有空间）
                             * 1.准备好要写什么了，这是第一步
                             * 2.第二步关心send-queue是否有空间
                             *
                             * 区别于 连接事件，通信事件-读；通信事件-写是程序自己发起的
                             * 通信事件-读: 一开始就要注册[acceptHandler 处理连接事件之后，就要注册] 为了进入死循环，一直调起，等待客户端发的包
                             * 通信事件-写：依赖以上关系，什么时候用什么时候注册
                             */
                            writeHandler(key);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理通信事件的写事件
     * @author abner
     * @date 2023/7/8 16:12
     * @version 1.0
     */
    private void writeHandler(SelectionKey key) {

        System.out.println("write handler...");
        SocketChannel client = (SocketChannel) key.channel();
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        buffer.flip();
        while (buffer.hasRemaining()) {
            try {

                client.write(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        buffer.clear();
        key.cancel();
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /***
     * 处理连接事件
     * @author abner
     * @date: 2023/7/8 15:21
     */
    public void acceptHandler(SelectionKey key) {
        try {
            ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
            SocketChannel client = ssc.accept(); //来啦，目的是调用accept接受客户端  fd7
            client.configureBlocking(false);

            ByteBuffer buffer = ByteBuffer.allocate(8192);

            /**
             * 1.select，poll：jvm里开辟一个数组 fd7 放进去
             * 2.epoll：  epoll_ctl(fd3,ADD,fd7,EPOLLIN
             *
             * SelectionKey.OP_READ:关注读事件，等着客户端发数据然后读数据
             *
             * 一个fd 只能关注一种事件
             *
             */
            client.register(selector, SelectionKey.OP_READ, buffer);
            System.out.println("-------------------------------------------");
            System.out.println("新客户端：" + client.getRemoteAddress());
            System.out.println("-------------------------------------------");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /***
     * 处理通信事件的读事件
     * @author abner
     * @date: 2023/7/8 15:21
     */
    public void readHandler(SelectionKey key) {
        SocketChannel client = (SocketChannel) key.channel();
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        buffer.clear();
        int read = 0;
        try {
            while (true) {
                read = client.read(buffer);
                if (read > 0) {
                    buffer.flip();
                    while (buffer.hasRemaining()) {
                        String str = StandardCharsets.UTF_8.decode(buffer).toString();
                        System.out.println("【客户端发来的数据】"+str);

                        //将数据写回给服务端
                        client.write(buffer);

                        /**
                         * SelectionKey.OP_WRITE:关注写事件
                         * 其实是关心send-queue是否有空间
                         */
                        client.register(key.selector(),SelectionKey.OP_WRITE,buffer);
                    }
                    buffer.clear();
                } else if (read == 0) {
                    break;
                } else {
                    client.close();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public static void main(String[] args) {
        SocketMultiplexingSingleThreadv1 service = new SocketMultiplexingSingleThreadv1();
        service.start();
    }
}

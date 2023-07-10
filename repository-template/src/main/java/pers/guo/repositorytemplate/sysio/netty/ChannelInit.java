package pers.guo.repositorytemplate.sysio.netty;

import io.netty.channel.*;

/**
 * 手动实现netty 代码
 *
 * 读事件的桥梁，服务端要支持多个客户端连接
 * 所以读事件必须是多例的
 *
 * @author abner
 * @version 1.0
 * @date 2023/7/10 23:41
 */
@ChannelHandler.Sharable
public class ChannelInit extends ChannelInboundHandlerAdapter {

    /**
     * 作为桥梁，帮助不同的客户端连接来绑定读事件处理方法
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        Channel client = ctx.channel();
        ChannelPipeline p = client.pipeline();
        p.addLast(new MyInHandler());//2,client::pipeline[ChannelInit,MyInHandler]
        ctx.pipeline().remove(this);
        //3,client::pipeline[MyInHandler]
    }

}

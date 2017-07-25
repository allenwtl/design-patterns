package com.allen.learn.socket;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.DefaultThreadFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.BasicConfigurator;

import java.net.InetSocketAddress;
import java.nio.channels.spi.SelectorProvider;
import java.util.concurrent.ThreadFactory;

public class TestNettyServer {

    static {
        BasicConfigurator.configure();
    }

    public static void main(String[] args) {
        //这就是主要的服务启动器
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        //=======================下面我们设置线程池
        //BOSS线程池
        EventLoopGroup bossLoopGroup = new NioEventLoopGroup(1);
        //WORK线程池：这样的申明方式，主要是为了向读者说明Netty的线程组是怎样工作的
        ThreadFactory threadFactory = new DefaultThreadFactory("work thread pool");
        //CPU个数
        int processorsNumber = Runtime.getRuntime().availableProcessors();
        EventLoopGroup workLoogGroup = new NioEventLoopGroup(processorsNumber * 2, threadFactory, SelectorProvider.provider());
        //指定Netty的Boss线程和work线程
        serverBootstrap.group(bossLoopGroup , workLoogGroup);
        //如果是以下的申明方式，说明BOSS线程和WORK线程共享一个线程池
        //（实际上一般的情况环境下，这种共享线程池的方式已经够了）
        //serverBootstrap.group(workLoogGroup);

        //========================下面我们设置我们服务的通道类型
        //只能是实现了ServerChannel接口的“服务器”通道类
        serverBootstrap.channel(NioServerSocketChannel.class);
        //当然也可以这样创建（那个SelectorProvider是不是感觉很熟悉？）
        //serverBootstrap.channelFactory(new ChannelFactory<NioServerSocketChannel>() {
        //  @Override
        //  public NioServerSocketChannel newChannel() {
        //      return new NioServerSocketChannel(SelectorProvider.provider());
        //  }
        //});

        //========================设置处理器
        //为了演示，这里我们设置了一组简单的ByteArrayDecoder和ByteArrayEncoder
        //Netty的特色就在这一连串“通道水管”中的“处理器”
        serverBootstrap.childHandler(new ChannelInitializer<NioSocketChannel>() {
            /* (non-Javadoc)
             * @see io.netty.channel.ChannelInitializer#initChannel(io.netty.channel.Channel)
             */
            @Override
            protected void initChannel(NioSocketChannel ch) throws Exception {
                ch.pipeline().addLast(new ByteArrayEncoder());
                ch.pipeline().addLast(new TCPServerHandler());
                ch.pipeline().addLast(new ByteArrayDecoder());
            }
        });

        //========================设置netty服务器绑定的ip和端口
        serverBootstrap.option(ChannelOption.SO_BACKLOG, 128);
        serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
        serverBootstrap.bind(new InetSocketAddress("0.0.0.0", 83));
        //还可以监控多个端口
        //serverBootstrap.bind(new InetSocketAddress("0.0.0.0", 84));



    }

}

@ChannelHandler.Sharable
class TCPServerHandler extends ChannelInboundHandlerAdapter {

    private static Log LOGGER = LogFactory.getLog(TCPServerHandler.class);

    private static AttributeKey<StringBuffer> content = AttributeKey.valueOf("content");

    public TCPServerHandler() {

    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        TCPServerHandler.LOGGER.info("super.channelRegistered(ctx)");
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        TCPServerHandler.LOGGER.info("super.channelUnregistered(ctx)");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        TCPServerHandler.LOGGER.info("super.channelActive(ctx)");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        TCPServerHandler.LOGGER.info("super.channelInactive(ctx)");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        TCPServerHandler.LOGGER.info("channelRead(ChannelHandlerContext ctx, Object msg)");
        /*
         * 我们使用IDE工具模拟长连接中的数据缓慢提交。
         * 由read方法负责接收数据，但只是进行数据累加，不进行任何处理
         * */
        ByteBuf byteBuf = (ByteBuf)msg;
        try {
            StringBuffer contextBuffer = new StringBuffer();
            while(byteBuf.isReadable()) {
                contextBuffer.append((char)byteBuf.readByte());
            }

            //加入临时区域
            StringBuffer content = ctx.attr(TCPServerHandler.content).get();
            if(content == null) {
                content = new StringBuffer();
                ctx.attr(TCPServerHandler.content).set(content);
            }
            content.append(contextBuffer);
        } catch(Exception e) {
            throw e;
        } finally {
            byteBuf.release();
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        TCPServerHandler.LOGGER.info("super.channelReadComplete(ctx)");
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        TCPServerHandler.LOGGER.info("super.userEventTriggered(ctx)");
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        TCPServerHandler.LOGGER.info("super.channelWritabilityChanged(ctx)");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        TCPServerHandler.LOGGER.info("super.exceptionCaught(ctx)");
    }
}
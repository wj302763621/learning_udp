package com.example.demo.init;

import com.example.demo.handle.UdpServerHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * server服务器
 * Created by wj on 2017/8/30.
 */
@Component
public class UdpServer {

    private static final Logger log= LoggerFactory.getLogger(UdpServer.class);

//    private static final int PORT = Integer.parseInt(System.getProperty("port", "7686"));

    @Async("myTaskAsyncPool")
    public void run(int udpReceivePort) {

        EventLoopGroup group = new NioEventLoopGroup();
        log.info("Server start!  Udp Receive msg Port:" + udpReceivePort );

        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST, true)
                    .handler(new UdpServerHandler());

            b.bind(udpReceivePort).sync().channel().closeFuture().await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

}

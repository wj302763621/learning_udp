package com.example.demo.handle;

import com.example.demo.init.StartupEvent;
import com.example.demo.mod.UdpRecord;
import com.example.demo.repository.mysql.UdpRepository;
import com.example.demo.repository.redis.RedisRepository;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 接受UDP消息，并保存至redis的list链表中
 * Created by wj on 2017/8/30.
 *
 */

public class UdpServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    private static final Logger log= LoggerFactory.getLogger(UdpServerHandler.class);

    //用来计算server接收到多少UDP消息
    private static int count = 0;

    @Override
    public void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) throws Exception {

        String receiveMsg = packet.content().toString(CharsetUtil.UTF_8);

        log.info("Received UDP Msg:" + receiveMsg);

        UdpRecord udpRecord = new UdpRecord();

        //判断接受到的UDP消息是否正确（未实现）
        if (StringUtils.isNotEmpty(receiveMsg) ){

            //计算接收到的UDP消息的数量
            count++;

            //获取UdpRepository对象，将接收UDP消息的日志保存至mysql中
            udpRecord.setUdpMsg(receiveMsg);
            udpRecord.setTime(getTime());
            UdpRepository udpRepository = (UdpRepository) StartupEvent.getBean(UdpRepository.class);
            udpRepository.save(udpRecord);

            //获取RedirRepository对象
            RedisRepository redisRepository = (RedisRepository) StartupEvent.getBean(RedisRepository.class);
            //将获取到的UDP消息保存至redis的list列表中
            redisRepository.lpush("udp:msg", receiveMsg);
            redisRepository.setKey("UDPMsgNumber", String.valueOf(count));


//            在这里可以返回一个UDP消息给对方，告知已接收到UDP消息，但考虑到这是UDP消息，此处可以注释掉
            ctx.write(new DatagramPacket(
                    Unpooled.copiedBuffer("QOTM: " + "Got UDP Message!" , CharsetUtil.UTF_8), packet.sender()));

        }else{
            log.error("Received Error UDP Messsage:" + receiveMsg);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        // We don't close the channel because we can keep serving requests.
    }

    public Timestamp getTime(){
        Date date = new Date();
        Timestamp time = new Timestamp(date.getTime());
        return time;
    }

}
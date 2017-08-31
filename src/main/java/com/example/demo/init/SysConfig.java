package com.example.demo.init;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by wj on 2017/8/30.
 */
@Component
@ConfigurationProperties(prefix="sysfig")
public class SysConfig {
    private int UdpReceivePort;//UDP消息接收端口

    //线程池信息
    private int CorePoolSize;

    private int MaxPoolSize;

    private int KeepAliveSeconds;

    private int QueueCapacity;

    public int getCorePoolSize() {
        return CorePoolSize;
    }

    public void setCorePoolSize(int corePoolSize) {
        CorePoolSize = corePoolSize;
    }

    public int getMaxPoolSize() {
        return MaxPoolSize;
    }

    public void setMaxPoolSize(int maxPoolSize) {
        MaxPoolSize = maxPoolSize;
    }

    public int getKeepAliveSeconds() {
        return KeepAliveSeconds;
    }

    public void setKeepAliveSeconds(int keepAliveSeconds) {
        KeepAliveSeconds = keepAliveSeconds;
    }

    public int getQueueCapacity() {
        return QueueCapacity;
    }

    public void setQueueCapacity(int queueCapacity) {
        QueueCapacity = queueCapacity;
    }

    public int getUdpReceivePort() {
        return UdpReceivePort;
    }

    public void setUdpReceivePort(int udpReceivePort) {
        UdpReceivePort = udpReceivePort;
    }
}

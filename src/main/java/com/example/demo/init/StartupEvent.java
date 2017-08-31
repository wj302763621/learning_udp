package com.example.demo.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 *
 * Created by wj on 2017/8/28.
 */

public class StartupEvent implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger log = LoggerFactory.getLogger(StartupEvent.class);

    private static ApplicationContext context;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        try {

            context = contextRefreshedEvent.getApplicationContext();

            SysConfig sysConfig = (SysConfig) context.getBean(SysConfig.class);

            //接收UDP消息并保存至redis中
            UdpServer udpServer = (UdpServer) StartupEvent.getBean(UdpServer.class);
            udpServer.run(sysConfig.getUdpReceivePort());


//            这里可以开启多个线程去执行不同的任务
//            此处为工作的内容，不便公开！


        } catch (Exception e) {
            log.error("Exception", e);
        }
    }

    public static Object getBean(Class beanName) {
        return context != null ? context.getBean(beanName) : null;
    }
}

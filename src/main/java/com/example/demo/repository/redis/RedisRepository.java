package com.example.demo.repository.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 链接redis
 * 实现list lpush和rpop
 * Created by wj on 2017/8/30.
 */


@Service
public class RedisRepository {
    private static final Logger log = LoggerFactory.getLogger(RedisRepository.class);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    //----------------String-----------------------
    public void setKey(String key,String value){
        redisTemplate.opsForValue().set(key, value);
    }


    //----------------list----------------------
    public Long lpush(String key, String val) throws Exception{
        log.info("UDP Msg保存至redis中，key:" + key + ",val:" + val);
        return redisTemplate.opsForList().leftPush(key, val);
    }

    public String rpop(String key) throws Exception {
        return redisTemplate.opsForList().rightPop(key);
    }

}

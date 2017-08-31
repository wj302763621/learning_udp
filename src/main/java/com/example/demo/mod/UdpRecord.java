package com.example.demo.mod;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * Created by wj on 2017/8/31.
 *
 * 用来记录接收的UDP消息的日志
 */
@Entity
@Table
public class UdpRecord {

    private long id;
    private String udpMsg;
    private Timestamp time;

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUdpMsg() {
        return udpMsg;
    }

    public void setUdpMsg(String udpMsg) {
        this.udpMsg = udpMsg;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}

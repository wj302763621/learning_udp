package com.example.demo.repository.mysql;

import com.example.demo.mod.UdpRecord;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by wj on 2017/8/31.
 */
public interface UdpRepository extends JpaRepository<UdpRecord,Long> {

}

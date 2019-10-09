package com.banzhuan.repairservice;

import com.banzhuan.repairservice.util.TimeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

public class RepairServiceApplicationTests {

	public static  void main(String[] args){
		System.out.println(TimeUtil.getMonth(157062202));
	}
}

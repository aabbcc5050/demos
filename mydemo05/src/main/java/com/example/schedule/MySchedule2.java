package com.example.schedule;


import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MySchedule2 implements Ordered{
	
	private Logger log=LoggerFactory.getLogger(getClass());
	
	@Scheduled(fixedRate = 60000)
	public void reportCurrentTime(){
		log.info("MySchedule2->当前时间 : [{}]",LocalDateTime.now());
	}	

	@Override
	public int getOrder() {
		return 2;
	}

}

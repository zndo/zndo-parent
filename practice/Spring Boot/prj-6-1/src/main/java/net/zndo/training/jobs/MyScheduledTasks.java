package net.zndo.training.jobs;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MyScheduledTasks {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	// @Scheduled(cron="cron表达式")
	// 毫秒单位
	@Scheduled(fixedRate = 5000)
	public void outputTimeJob() {
		System.out.println("滴答滴答，--- " + dateFormat.format(new Date()));
	}

}

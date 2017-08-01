package net.zndo.training.jobs;

import java.util.Random;

import org.apache.log4j.LogManager;
import org.springframework.stereotype.Component;
import org.apache.log4j.Logger;

/**
 * 同步任务
 * @author zndo
 *
 */
@Component
public class MySyncTask {

	private static final Logger logger = LogManager.getLogger(MySyncTask.class);
	private static Random random = new Random();

	public void job1() throws Exception {
		logger.info("任务 1  开始 ...");

		Long startTime = System.currentTimeMillis();
		Thread.sleep(random.nextInt(10000));
		Long endTime = System.currentTimeMillis();
		Long spendTime = endTime - startTime;

		logger.info("任务 1  结束，耗时：" + spendTime);
	}

	public void job2() throws Exception {
		logger.info("任务 2  开始 ...");

		Long startTime = System.currentTimeMillis();
		Thread.sleep(random.nextInt(10000));
		Long endTime = System.currentTimeMillis();
		Long spendTime = endTime - startTime;

		logger.info("任务 2  结束，耗时：" + spendTime);
	}

	public void job3() throws Exception {
		logger.info("任务 3  开始 ...");

		Long startTime = System.currentTimeMillis();
		Thread.sleep(random.nextInt(10000));
		Long endTime = System.currentTimeMillis();
		Long spendTime = endTime - startTime;

		logger.info("任务 3  结束，耗时：" + spendTime);
	}

}

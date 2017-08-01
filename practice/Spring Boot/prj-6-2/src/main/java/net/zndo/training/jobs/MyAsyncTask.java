package net.zndo.training.jobs;

import java.util.Random;
import java.util.concurrent.Future;

import org.apache.log4j.LogManager;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.apache.log4j.Logger;

/**
 * 异步任务
 * @author zndo
 *
 */
@Component
public class MyAsyncTask {

	private static final Logger logger = LogManager.getLogger(MyAsyncTask.class);
	private static Random random = new Random();

	@Async
	public Future<String> job1() throws Exception {
		logger.info("任务 1  开始 ...");

		Long startTime = System.currentTimeMillis();
		Thread.sleep(1000);
		Long endTime = System.currentTimeMillis();
		Long spendTime = endTime - startTime;

		logger.info("任务 1  结束，耗时：" + spendTime);
		
		return new AsyncResult<>("异步回调结果：任务 1 已完成");
	}

	@Async
	public Future<String> job2() throws Exception {
		logger.info("任务 2  开始 ...");

		Long startTime = System.currentTimeMillis();
		Thread.sleep(random.nextInt(1500));
		Long endTime = System.currentTimeMillis();
		Long spendTime = endTime - startTime;

		logger.info("任务 2  结束，耗时：" + spendTime);
		
		return new AsyncResult<>("异步回调结果：任务 2 已完成");
	}

	@Async
	public Future<String> job3() throws Exception {
		logger.info("任务 3  开始 ...");

		Long startTime = System.currentTimeMillis();
		Thread.sleep(random.nextInt(2000));
		Long endTime = System.currentTimeMillis();
		Long spendTime = endTime - startTime;

		logger.info("任务 3  结束，耗时：" + spendTime);
		
		return new AsyncResult<>("异步回调结果：任务 3 已完成");
	}

}

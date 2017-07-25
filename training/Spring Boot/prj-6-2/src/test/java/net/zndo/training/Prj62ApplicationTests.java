package net.zndo.training;

import java.util.concurrent.Future;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.zndo.training.jobs.MyAsyncTask;
import net.zndo.training.jobs.MySyncTask;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Prj62ApplicationTests {

	@Autowired
	private MySyncTask syncTask;

	@Autowired
	private MyAsyncTask asyncTask;

	@Test
	public void contextLoads() {
	}

	// 同步放放风测试
	@Test
	public void testSyncJob() throws Exception {

		syncTask.job1();
		syncTask.job2();
		syncTask.job3();

		// 同步任务顺序执行，必须等到队列中的当前任务结束才开始下一任务

		// : 任务 1 开始 ...
		// : 任务 1 结束，耗时：8534
		// : 任务 2 开始 ...
		// : 任务 2 结束，耗时：1315
		// : 任务 3 开始 ...
		// : 任务 3 结束，耗时：4179
	}

	// 异步方法测试
	@Test
	public void testAsyncJob() throws Exception {

		long async_start = System.currentTimeMillis();

		Future<String> async1 = asyncTask.job1();
		Future<String> async2 = asyncTask.job2();
		Future<String> async3 = asyncTask.job3();

		while (true) {
			if (async1.isDone() && async2.isDone() && async3.isDone()) {
				// 三个异步作业都处理完毕，挂起
				break;
			}
			Thread.sleep(1000);
		}

		long async_end = System.currentTimeMillis();

		System.out.println("任务全部完成，总耗时：" + (async_end - async_start) + "毫秒");

		// 异步任务只发出指令，并不等待任务结束
		// : 任务 1 开始 ...
		// : 任务 2 开始 ...
		// : 任务 3 开始 ...
		//

		// : 任务 2 开始 ...
		// : 任务 1 开始 ...
		// : 任务 3 开始 ...

		// 2017-07-19 16:28:39.847 : 任务 2 开始 ...
		// 2017-07-19 16:28:39.847 : 任务 1 开始 ...
		// 2017-07-19 16:28:39.848 : 任务 3 开始 ...

	}

}

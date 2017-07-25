package net.zndo.training.prj41.aop;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Web 日志切面
 *
 */
@Aspect
@Component
public class L2WebLogAspect {

	private static final Logger LOGGER = LogManager.getLogger();

	// 基本类型成员变量会存在同步问题，所以使用 ThreadLocal
	ThreadLocal<Long> startTimeMillis = new ThreadLocal<>();

	/**
	 * 切点，net.zndo 包子目录递归 web.controller 下的所有 Controller 后缀的类的所有 public 方法
	 */
	@Pointcut("execution(public * net.zndo..web.controller.*Controller.*(..))")
	public void webLog() {
	}

	/**
	 * 前置通知
	 * 
	 * @param joinPoint
	 */
	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {

		// 记录请求开始时间
		startTimeMillis.set(System.currentTimeMillis());

		LOGGER.debug("接收到请求 ...");
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attrs.getRequest();

		// 记录请求信息
		LOGGER.debug("请求地址 : " + request.getRequestURL().toString());
		LOGGER.debug("请求方法 : " + request.getMethod());
		LOGGER.debug("请求 IP : " + request.getRemoteAddr());
		LOGGER.debug(
				"类型方法 : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
		LOGGER.debug("请求参数 : " + Arrays.toString(joinPoint.getArgs()));

	}

	/**
	 * 返回后置通知
	 */
	@AfterReturning(returning = "ret", pointcut = "webLog()")
	public void doAfterReturning(Object ret) throws Throwable {
		LOGGER.debug("响应已完成");

		LOGGER.debug("响应内容 : " + ret);
		LOGGER.debug("响应时长 : " + (String.valueOf(System.currentTimeMillis() - startTimeMillis.get())) + " 毫秒");
	}

	/**
	 * 抛出异常后置处理
	 */
	@AfterThrowing("webLog()")
	public void doAfterThrowing() {
		LOGGER.error("抛出异常后置处理，比如跳转到错误页面显示详细信息。");
	}

}

package cn.codeyang.auth.aop.logging;

import cn.codeyang.framework.config.YangConstants;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.env.Environment;

import java.util.Arrays;

/**
 * @author yangzhongyang
 */
@Aspect
@Slf4j
public class LoggingAspect {
	private final Environment env;

	public LoggingAspect(Environment env) {
		this.env = env;
	}

	/**
	 * 匹配所有的repository、services、rest
	 */
	@Pointcut("within(@org.springframework.stereotype.Repository *)" +
			" || within(@org.springframework.stereotype.Service *)" +
			" || within(@org.springframework.stereotype.Controller *)")
	public void springBeanPointcut() {
	}

	/**
	 * 匹配所有主要的package
	 */
	@Pointcut("within(cn.codeyang.auth.mapper..*)" +
			" || within(cn.codeyang.auth.service..*)" +
			" || within(cn.codeyang.auth.web.rest..*)")
	public void applicationPackagePointcut() {
	}

	@AfterThrowing(pointcut = "applicationPackagePointcut() && springBeanPointcut()", throwing = "e")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
		if (env.acceptsProfiles(YangConstants.SPRING_PROFILE_DEVELOPMENT)) {
			log.error("Exception in {}.{}() with cause = \'{}\' and exception = \'{}\'",
					joinPoint.getSignature().getDeclaringTypeName(),
					joinPoint.getSignature().getName(),
					e.getCause() != null ? e.getCause() : "NUL",
					e.getMessage(),
					e);
		} else {
			log.error("Exception in {}.{}() with cause = {}",
					joinPoint.getSignature().getDeclaringTypeName(),
					joinPoint.getSignature().getName(),
					e.getCause() != null ? e.getCause() : "NULL");
		}
	}

	@Around("applicationPackagePointcut() && springBeanPointcut()")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		if (log.isDebugEnabled()) {
			log.debug("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
					joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
		}
		try {
			Object result = joinPoint.proceed();
			if (log.isDebugEnabled()) {
				log.debug("Exit: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
						joinPoint.getSignature().getName(), result);
			}
			return result;
		} catch (IllegalArgumentException e) {
			log.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
					joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());

			throw e;
		}
	}
}

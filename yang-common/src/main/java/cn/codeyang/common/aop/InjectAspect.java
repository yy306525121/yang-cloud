package cn.codeyang.common.aop;

import cn.codeyang.common.utils.FieldInjectUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author akademos
 * aop实现动态注入属性值
 */
@Component
@Aspect
public class InjectAspect {
	@Autowired
	private FieldInjectUtils fieldInjectUtils;

	@Around("@annotation(cn.codeyang.common.annotation.InjectField)")
	public Object doInject(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		Object result = proceedingJoinPoint.proceed();

		if (result instanceof Collection) {
			// 如果方法返回结果是一个结果集
			this.fieldInjectUtils.setInjectFileValueForCollection(((Collection) result));
		}

		return result;
	}
}

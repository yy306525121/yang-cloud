package cn.codeyang.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectField {
	/**
	 * 需要注入的属性所属类
	 * @return
	 */
	Class<?> beanClass();

	String param();

	String method();

	String targetField();

}

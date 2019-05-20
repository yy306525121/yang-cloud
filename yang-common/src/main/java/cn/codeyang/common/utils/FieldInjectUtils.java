package cn.codeyang.common.utils;

import cn.codeyang.common.annotation.InjectField;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;

/**
 * @author akademos
 */
@Component
public class FieldInjectUtils implements ApplicationContextAware {
	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public void setInjectFileValueForCollection(Collection collection) throws Exception {
		//1. 获取到结果集的class对象

		Class<?> clazz = collection.iterator().getClass();

		//2. 获取class中的一个属性
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			InjectField annotationField = field.getAnnotation(InjectField.class);

			if (annotationField == null) {
				continue;
			}

			field.setAccessible(true);

			/**
			 * 拿到注解中指定的beanClass
			 */
			Object bean = this.applicationContext.getBean(annotationField.beanClass());
			Method method = annotationField.beanClass()
								.getMethod(annotationField.method(),
								clazz.getDeclaredField(annotationField.param()).getType());
			Field paramField = clazz.getDeclaredField(annotationField.param());
			paramField.setAccessible(true);

			Field targetField = null;
			boolean injectFromField = StringUtils.isNotEmpty(annotationField.targetField());

			for (Object obj : collection) {
				Object paramValue = paramField.get(obj);
				if (paramValue == null || "0".equals(paramValue)) {
					continue;
				}

				/**
				 * 拿到结果
				 */
				Object value = method.invoke(bean, paramValue);
				if (injectFromField) {
					if (value != null) {
						if (targetField == null) {
							targetField = value.getClass().getDeclaredField(annotationField.targetField());
							targetField.setAccessible(true);
						}

						value = targetField.get(value);
					}
				}

				field.set(obj, value);

			}
		}
	}
}

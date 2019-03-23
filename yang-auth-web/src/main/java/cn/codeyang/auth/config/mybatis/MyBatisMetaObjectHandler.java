package cn.codeyang.auth.config.mybatis;


import cn.codeyang.auth.api.entity.User;
import cn.codeyang.common.util.SecurityUtils;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author yangzhongyang
 */
@Component
@Slf4j
public class MyBatisMetaObjectHandler implements MetaObjectHandler {
	@Override
	public void insertFill(MetaObject metaObject) {
		log.debug("start insert fill ...");

		Object currentUser = SecurityUtils.getCurrentUser();
		this.setFieldValByName("createTime", new Date(), metaObject);
		if (currentUser instanceof User) {
			this.setFieldValByName("createUid", ((User)currentUser).getId(), metaObject);
		} else {
			this.setFieldValByName("createUid", 0, metaObject);
		}
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		log.debug("start update fill ...");

		Object currentUser = SecurityUtils.getCurrentUser();
		this.setFieldValByName("updateTime", new Date(), metaObject);
		if (currentUser instanceof User) {
			this.setFieldValByName("updateUid", ((User)currentUser).getId(), metaObject);
		} else {
			this.setFieldValByName("updateUid", 0, metaObject);
		}

	}
}

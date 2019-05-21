package cn.codeyang.auth.api.service;

import cn.codeyang.auth.api.entity.Role;
import cn.codeyang.auth.api.entity.query.RoleQuery;
import org.springframework.data.domain.Page;

/**
 * @author yangzhongyang
 */
public interface RoleService {
	Page<Role> findByCondition(Integer page, Integer size, RoleQuery roleQuery);
}

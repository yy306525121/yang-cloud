package cn.codeyang.auth.service.impl;

import cn.codeyang.auth.api.entity.Role;
import cn.codeyang.auth.api.entity.query.RoleQuery;
import cn.codeyang.auth.api.service.RoleService;
import cn.codeyang.auth.repositories.RoleRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

/**
 * @author yangzhongyang
 */
@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Page<Role> findByCondition(Integer page, Integer size, final RoleQuery roleQuery) {
		Role role = new Role();

		role.setName("ROLE");
		ExampleMatcher matcher = ExampleMatcher.matching();
		if (StringUtils.isNotEmpty(roleQuery.getName())) {
			matcher.withMatcher("name", ExampleMatcher.GenericPropertyMatchers.startsWith());
		}

		Example<Role> example = Example.of(role, matcher);

		Pageable pageable = PageRequest.of(page, size);
		return roleRepository.findAll(example, pageable);
	}
}

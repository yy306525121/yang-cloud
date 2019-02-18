package cn.codeyang.auth.service.impl;

import cn.codeyang.auth.entity.Authority;
import cn.codeyang.auth.service.AuthorityService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author yangzhongyang
 */


public class AuthorityServiceImplTest {
	@Autowired
	private AuthorityService authorityService;

	@Test
	public void findAuthoritiesByUserId() {
		List<Authority> authoritiesByUserId = authorityService.findAuthoritiesByUserId(1);
		System.out.println(authoritiesByUserId);
	}
}
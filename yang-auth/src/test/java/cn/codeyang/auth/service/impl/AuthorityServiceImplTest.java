package cn.codeyang.auth.service.impl;

import cn.codeyang.auth.entity.Authority;
import cn.codeyang.auth.service.AuthorityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author yangzhongyang
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class AuthorityServiceImplTest {
	@Autowired
	private AuthorityService authorityService;

	@Test
	public void findAuthoritiesByUserId() {
		List<Authority> authoritiesByUserId = authorityService.findAuthoritiesByUserId(1);
		System.out.println(authoritiesByUserId);
	}
}
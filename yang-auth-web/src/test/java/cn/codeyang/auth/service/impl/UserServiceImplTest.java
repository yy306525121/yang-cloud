package cn.codeyang.auth.service.impl;

import cn.codeyang.auth.api.entity.User;
import cn.codeyang.auth.api.entity.query.UserQuery;
import cn.codeyang.auth.api.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author akafra
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(value = "dev")
public class UserServiceImplTest {

	@Autowired
	private UserService userService;

	@Test
	public void loadUserByUsername() {
		User yang = (User) userService.loadUserByUsername("yang");
		System.out.println("Result:" + yang);
	}

	@Test
	public void registerUser() {
	}

	@Test
	public void activateRegistration() {
	}


	@Test
	public void findByCondition() {
		UserQuery user = new UserQuery();
		user.setUsername("yang");
		user.setNickname("杨大哥1");
		Page<User> pageResult = userService.findByCondition(0, 10, user);
		System.out.println("Result: " + pageResult.getContent().size());
	}

	@Test
	public void findByCondition2() {
		UserQuery user = new UserQuery();
		user.setUsername("yang");
		user.setNickname("杨大哥");
		Page<User> pageResult = userService.findByCondition2(0, 10, user);
		System.out.println("Result: " + pageResult.getContent().size());
	}
}

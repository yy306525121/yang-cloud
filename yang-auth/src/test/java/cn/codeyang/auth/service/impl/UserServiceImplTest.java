package cn.codeyang.auth.service.impl;

import cn.codeyang.auth.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yangzhongyang
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class UserServiceImplTest {
	@Autowired
	private UserService userService;

	@Test
	public void selectAll(){
		System.out.println(userService.list());
	}
}
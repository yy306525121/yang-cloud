package cn.codeyang.auth.service.impl;

import cn.codeyang.auth.api.entity.User;
import cn.codeyang.auth.api.service.MailService;
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
public class MailServiceImplTest {

	@Autowired
	private MailService mailService;

	@Test
	public void sendEmail() {
		User user = new User();
		user.setLangKey("zh-cn");
		user.setEmail("akademos_yang@163.com");
		mailService.sendActivationEmail(user);
	}

	@Test
	public void sendEmailFromTemplate() {
	}

	@Test
	public void sendActivationEmail() {
	}

	@Test
	public void sendCreationEmail() {
	}

	@Test
	public void sendPasswordResetMail() {
	}
}

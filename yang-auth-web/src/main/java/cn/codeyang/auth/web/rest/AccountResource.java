package cn.codeyang.auth.web.rest;

import cn.codeyang.auth.api.entity.User;
import cn.codeyang.auth.api.service.UserService;
import cn.codeyang.auth.component.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author yangzhongyang
 */
@RestController
@RequestMapping("/api/account")
public class AccountResource {
	private final UserService userService;
	private final RabbitMQSender rabbitMQSender;

	@Autowired
	public AccountResource(UserService userService, RabbitMQSender rabbitMQSender) {
		this.userService = userService;
		this.rabbitMQSender = rabbitMQSender;
	}

	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public void registerAccount(User user) throws Exception {
		User newUser = userService.registerUser(user);
		rabbitMQSender.sendEmail(newUser, new HashMap<>());
		//mailService.sendActivationEmail(newUser);
	}

	@PostMapping("/activate")
	public void activateAccount(String key) throws Exception {
		User user = userService.activateRegistration(key);
		if (user == null) {
			throw new Exception("激活码错误");
		}
	}

}

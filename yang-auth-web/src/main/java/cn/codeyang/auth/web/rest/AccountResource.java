package cn.codeyang.auth.web.rest;

import cn.codeyang.auth.api.entity.User;
import cn.codeyang.auth.api.service.MailService;
import cn.codeyang.auth.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangzhongyang
 */
@RestController
@RequestMapping("/api/account")
public class AccountResource {
	private final UserService userService;
	private final MailService mailService;

	@Autowired
	public AccountResource(UserService userService, MailService mailService) {
		this.userService = userService;
		this.mailService = mailService;
	}

	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public void registerAccount(User user) throws Exception {
		User newUser = userService.registerUser(user);
		//rabbitMQSender.sendEmail(newUser, new HashMap<>());
		mailService.sendActivationEmail(user);
		//mailService.sendActivationEmail(newUser);
	}


}

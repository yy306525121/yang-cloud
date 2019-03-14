package cn.codeyang.auth.web.rest;

import cn.codeyang.auth.entity.User;
import cn.codeyang.auth.service.MailService;
import cn.codeyang.auth.service.UserService;
import cn.codeyang.auth.web.rest.vm.ManagedUserVM;
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
	public void registerAccount(ManagedUserVM managedUserVM) throws Exception {
		User newUser = userService.registerUser(managedUserVM, managedUserVM.getPassword());
		mailService.sendActivationEmail(newUser);
	}

	@PostMapping("/activate")
	public void activateAccount(String key) throws Exception {
		User user = userService.activateRegistration(key);
		if (user == null) {
			throw new Exception("激活码错误");
		}
	}


}

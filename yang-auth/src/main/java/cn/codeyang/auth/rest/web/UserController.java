package cn.codeyang.auth.rest.web;

import cn.codeyang.auth.entity.User;
import cn.codeyang.auth.service.UserService;
import cn.codeyang.auth.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yangzhongyang
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping("")
	public List<User> list(){
		return userService.list();
	}

	@GetMapping("/info")
	public User info(){
		return SecurityUtils.getCurrentUser();
	}
}

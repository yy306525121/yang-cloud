package cn.codeyang.auth.rest.web;

import cn.codeyang.auth.entity.User;
import cn.codeyang.auth.service.UserService;
import cn.codeyang.auth.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yangzhongyang
 */
@RestController
@Api(value = "用户管理", description = "用户管理")
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserService userService;


	@ApiOperation(value = "用户列表")
	@GetMapping("list")
	public List<User> list(){
		return userService.list();
	}

	@ApiOperation(value = "显示当前用户")
	@GetMapping("/info")
	public User info(){
		return SecurityUtils.getCurrentUser();
	}
}

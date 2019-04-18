package cn.codeyang.auth.web.rest;

import cn.codeyang.auth.api.entity.User;
import cn.codeyang.auth.api.service.UserService;
import cn.codeyang.common.util.SecurityUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangzhongyang
 */
@RestController
@Api(value = "用户管理", description = "用户管理")
@RequestMapping("/api/user")
public class UserResource {
	@Autowired
	private UserService userService;


	@ApiOperation(value = "用户列表")
	@PostMapping("list")
	public ResponseEntity<IPage<User>> list(long current, long size, String username){
		Page<User> pageCondition = new Page<>(current, size);
		IPage<User> result = userService.query()
				.likeRight(StringUtils.isNotBlank(username), "" +
						"username", username)
				.orderByDesc("create_time")
				.page(pageCondition);

		return ResponseEntity.ok(result);
	}

	@ApiOperation(value = "显示当前用户")
	@GetMapping("/info")
	public User info(){
		Object currentUser = SecurityUtils.getCurrentUser();
		if (currentUser instanceof User){
			return (User) currentUser;
		}

		return null;
	}

}

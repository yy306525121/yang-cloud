package cn.codeyang.auth.api.service;

import cn.codeyang.auth.api.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author yangzhongyang
 */
public interface UserService extends IService<User>, UserDetailsService {
	User registerUser(User user) throws Exception;

	User activateRegistration(String key);
}

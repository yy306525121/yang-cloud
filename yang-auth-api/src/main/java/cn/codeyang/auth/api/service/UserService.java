package cn.codeyang.auth.api.service;

import cn.codeyang.auth.api.entity.User;
import cn.codeyang.auth.api.entity.query.UserQuery;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

/**
 * @author yangzhongyang
 */
public interface UserService extends UserDetailsService {
	User registerUser(User user) throws Exception;

	Optional<User> activateRegistration(String key);

	Page<User> findByCondition(int page, int size, UserQuery userQuery);
	Page<User> findByCondition2(int page, int size, UserQuery userQuery);

}

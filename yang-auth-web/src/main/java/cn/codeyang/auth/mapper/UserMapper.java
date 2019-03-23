package cn.codeyang.auth.mapper;

import cn.codeyang.auth.api.entity.Role;
import cn.codeyang.auth.api.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @author yangzhongyang
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
	User findByUsername(String username);

	User findOneByUsername(String username);

	User findByEmail(String email);

	User findOneByEmailIgnoreCase(String email);

	void addRole(@Param("userId") Long userId, @Param("roles") Set<Role> roles);

	User findOneByActivationKey(String key);

	void updateActivate(User userDB);
}

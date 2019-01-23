package cn.codeyang.auth.mapper;

import cn.codeyang.auth.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @author yangzhongyang
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
	User findByUsername(String username);

	User findByEmail(String email);
}

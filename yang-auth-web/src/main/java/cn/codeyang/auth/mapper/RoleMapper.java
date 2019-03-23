package cn.codeyang.auth.mapper;

import cn.codeyang.auth.api.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yangzhongyang
 */
@Repository
public interface RoleMapper extends BaseMapper<Role> {
	List<Role> findByUserId(Long userId);

	Role selectByName(String user);
}

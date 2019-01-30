package cn.codeyang.auth.mapper;

import cn.codeyang.auth.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yangzhongyang
 */
@Repository
public interface RoleMapper extends BaseMapper<Role> {
	List<Role> findByUserId(Integer userId);
}

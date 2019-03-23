package cn.codeyang.auth.mapper;

import cn.codeyang.auth.api.entity.Authority;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yangzhongyang
 */
@Repository
public interface AuthorityMapper extends BaseMapper<Authority> {
	/**
	 * 根据userId查询用户权限
	 * @param userId 用户ID
	 */
	List<Authority> findAuthoritiesByUserId(Long userId);
}

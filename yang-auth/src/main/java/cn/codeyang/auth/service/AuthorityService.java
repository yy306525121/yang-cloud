package cn.codeyang.auth.service;

import cn.codeyang.auth.entity.Authority;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author yangzhongyang
 */
public interface AuthorityService extends IService<Authority> {
	/**
	 * 根据userId查询用户权限
	 * @param userId 用户ID
	 */
	List<Authority> findAuthoritiesByUserId(Long userId);
}

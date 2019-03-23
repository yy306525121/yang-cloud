package cn.codeyang.auth.service.impl;


import cn.codeyang.auth.api.entity.Authority;
import cn.codeyang.auth.api.service.AuthorityService;
import cn.codeyang.auth.mapper.AuthorityMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yangzhongyang
 */
@Service
public class AuthorityServiceImpl extends ServiceImpl<AuthorityMapper, Authority> implements AuthorityService {
	@Autowired
	private AuthorityMapper authorityMapper;
	/**
	 * 根据userId查询用户权限
	 * @param userId 用户ID
	 */
	public List<Authority> findAuthoritiesByUserId(Long userId){
		return authorityMapper.findAuthoritiesByUserId(userId);
	}
}

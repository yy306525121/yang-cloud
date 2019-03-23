package cn.codeyang.auth.service.impl;

import cn.codeyang.auth.api.entity.Role;
import cn.codeyang.auth.api.service.RoleService;
import cn.codeyang.auth.mapper.RoleMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yangzhongyang
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
	@Autowired
	private RoleMapper roleMapper;
	@Override
	public List<Role> selectByUserId(Long userId) {
		return roleMapper.findByUserId(userId);
	}
}

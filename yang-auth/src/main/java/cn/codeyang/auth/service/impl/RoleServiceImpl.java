package cn.codeyang.auth.service.impl;

import cn.codeyang.auth.entity.Role;
import cn.codeyang.auth.mapper.RoleMapper;
import cn.codeyang.auth.service.RoleService;
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
	public List<Role> selectByUserId(Integer userId) {
		return roleMapper.findByUserId(userId);
	}
}

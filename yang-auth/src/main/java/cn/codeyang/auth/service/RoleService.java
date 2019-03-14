package cn.codeyang.auth.service;

import cn.codeyang.auth.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author yangzhongyang
 */
public interface RoleService extends IService<Role> {
	List<Role> selectByUserId(Long userId);
}

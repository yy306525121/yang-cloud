package cn.codeyang.auth.api.service;

import cn.codeyang.auth.api.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author yangzhongyang
 */
public interface RoleService extends IService<Role> {
	List<Role> selectByUserId(Long userId);
}

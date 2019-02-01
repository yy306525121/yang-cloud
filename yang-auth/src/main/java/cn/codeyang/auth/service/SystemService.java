package cn.codeyang.auth.service;

import cn.codeyang.auth.entity.System;
import cn.codeyang.auth.service.dto.SystemDTO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 系统服务
 * @author yangzhongyang
 */
public interface SystemService extends IService<System> {

	/**
	 * 注册新系统
	 * @param systemDTO
	 * @return
	 */
	boolean registerSystem(SystemDTO systemDTO);

	/**
	 * 更新系统信息
	 * @param systemDTO
	 * @return
	 */
	SystemDTO updateSystem(SystemDTO systemDTO);

	/**
	 * 删除系统
	 * @param id
	 */
	void deleteSystem(Integer id);


	SystemDTO getOneByName(String name);

	SystemDTO getOneByTitle(String title);
}

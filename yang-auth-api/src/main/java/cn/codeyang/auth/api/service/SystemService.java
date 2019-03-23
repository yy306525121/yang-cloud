package cn.codeyang.auth.api.service;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author yangzhongyang
 */
public interface SystemService extends IService<System> {

	/**
	 * 注册新系统
	 *
	 * @param system
	 * @return
	 */
	boolean registerSystem(System system);

	/**
	 * 更新系统信息
	 *
	 * @param system
	 * @return
	 */
	void updateSystem(System system);

	/**
	 * 删除系统
	 *
	 * @param id
	 */
	void deleteSystem(Long id);


	System getOneByName(String name);

	System getOneByTitle(String title);
}

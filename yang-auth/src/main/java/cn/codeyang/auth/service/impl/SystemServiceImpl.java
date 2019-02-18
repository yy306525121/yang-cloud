package cn.codeyang.auth.service.impl;

import cn.codeyang.auth.entity.System;
import cn.codeyang.auth.entity.enums.BaseStatusEnum;
import cn.codeyang.auth.mapper.SystemMapper;
import cn.codeyang.auth.service.SystemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yangzhongyang
 */
@Service
@Transactional
@Slf4j
public class SystemServiceImpl extends ServiceImpl<SystemMapper, System> implements SystemService {

	@Override
	public boolean registerSystem(System system) {
		system.setStatus(BaseStatusEnum.NORMAL);
		return this.baseMapper.insert(system) > 0;
	}

	@Override
	public void updateSystem(System system) {
		System systemDB = this.baseMapper.selectById(system.getId());
		if (systemDB != null) {
			this.baseMapper.updateById(system);
		}
	}

	@Override
	public void deleteSystem(Integer id) {
		System system = new System();
		system.setId(id);
		system.setStatus(BaseStatusEnum.DELETED);
		baseMapper.updateById(system);
	}

	@Override
	public System getOneByName(String name) {
		return this.baseMapper.selectOneByName(name);
	}

	@Override
	public System getOneByTitle(String title) {
		return this.getBaseMapper().selectOneByTitle(title);

	}

}

package cn.codeyang.auth.service.impl;

import cn.codeyang.auth.entity.System;
import cn.codeyang.auth.entity.enums.BaseStatusEnum;
import cn.codeyang.auth.mapper.SystemMapper;
import cn.codeyang.auth.service.SystemService;
import cn.codeyang.auth.service.dto.SystemDTO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author yangzhongyang
 */
@Service
public class SystemServiceImpl extends ServiceImpl<SystemMapper, System> implements SystemService {

	@Override
	public boolean registerSystem(SystemDTO systemDTO) {
		System system = new System();
		fillSystem(systemDTO, system);
		system.setStatus(BaseStatusEnum.NORMAL);
		return this.baseMapper.insert(system) > 0;
	}

	@Override
	public SystemDTO updateSystem(SystemDTO systemDTO) {
		System systemDB = this.baseMapper.selectById(systemDTO.getId());
		if (systemDB != null) {
			System system = new System();
			fillSystem(systemDTO, system);
			this.baseMapper.updateById(system);

			systemDB = baseMapper.selectById(systemDTO.getId());
			return new SystemDTO(systemDB);
		}

		return null;

	}

	@Override
	public void deleteSystem(Integer id) {
		System system = new System();
		system.setId(id);
		system.setStatus(BaseStatusEnum.DELETED);
		baseMapper.updateById(system);
	}

	@Override
	public SystemDTO getOneByName(String name) {
		System system = this.baseMapper.selectOneByName(name);

		if (system == null) {
			return null;
		}

		return new SystemDTO(system);
	}

	@Override
	public SystemDTO getOneByTitle(String title) {
		System system = this.getBaseMapper().selectOneByTitle(title);

		if (system == null) {
			return null;
		}

		return new SystemDTO(system);
	}

	private void fillSystem(SystemDTO systemDTO, System system) {

		if (systemDTO.getId() != null) {
			system.setId(systemDTO.getId());
		}

		if (StringUtils.isNotEmpty(systemDTO.getCode())) {
			system.setCode(systemDTO.getCode());
		}

		if (StringUtils.isNotEmpty(systemDTO.getDescription())) {
			system.setDescription(systemDTO.getDescription());
		}

		if (StringUtils.isNotEmpty(systemDTO.getIcon())) {
			system.setIcon(systemDTO.getIcon());
		}

		if (StringUtils.isNotEmpty(systemDTO.getName())) {
			system.setName(systemDTO.getName());
		}

		if (StringUtils.isNotEmpty(systemDTO.getTitle())) {
			system.setTitle(systemDTO.getTitle());
		}
	}
}

package cn.codeyang.auth.service.impl;

import cn.codeyang.auth.entity.Organization;
import cn.codeyang.auth.entity.enums.BaseStatusEnum;
import cn.codeyang.auth.mapper.OrganizationMapper;
import cn.codeyang.auth.service.OrganizationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author yangzhongyang
 */
@Service
public class OrganizationServiceImpl extends ServiceImpl<OrganizationMapper, Organization> implements OrganizationService {
	@Override
	public Organization getByNameEq(String name) {
		return this.getBaseMapper().getByNameEq(name);
	}

	@Override
	public boolean createOrganization(Organization organization) {
		if (organization.getPid() == null) {
			organization.setPid(0);
		}
		return this.getBaseMapper().insert(organization) > 0;
	}

	@Override
	public void deleteOrganization(Integer id) {
		Organization organization = new Organization();
		organization.setId(id);
		organization.setStatus(BaseStatusEnum.DELETED);
		baseMapper.updateById(organization);
	}
}

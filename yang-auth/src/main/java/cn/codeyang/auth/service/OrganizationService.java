package cn.codeyang.auth.service;

import cn.codeyang.auth.entity.Organization;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author yangzhongyang
 */
public interface OrganizationService extends IService<Organization> {
	Organization getByNameEq(String name);

	boolean createOrganization(Organization organization);

	void deleteOrganization(Integer id);

	Organization selectOne(Organization organization);
}

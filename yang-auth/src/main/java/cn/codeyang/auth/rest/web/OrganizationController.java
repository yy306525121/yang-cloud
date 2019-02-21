package cn.codeyang.auth.rest.web;

import cn.codeyang.auth.entity.Organization;
import cn.codeyang.auth.service.OrganizationService;
import cn.codeyang.common.constant.AuthoritiesConstants;
import cn.codeyang.common.http.utils.HttpResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangzhongyang
 */
@RestController
@Api(value = "机构管理", description = "机构管理")
@Slf4j
@RequestMapping("/api/organization")
public class OrganizationController {

	private OrganizationService organizationService;

	public OrganizationController(OrganizationService organizationService){
		this.organizationService = organizationService;
	}

	@ApiOperation(value = "查询所有机构")
	@PostMapping("/list")
	public IPage<Organization> list(Page<Organization> pageCondition, String name, int pid) {
		return organizationService.query()
				.like(StringUtils.isNotEmpty(name), "name", name)
				.eq(pid >= 0, "pid", pid)
				.orderByDesc("create_time")
				.page(pageCondition);
	}

	@ApiOperation(value = "创建机构")
	@PreAuthorize("hasRole('"+ AuthoritiesConstants.ADMIN +"')")
	@PostMapping("/create")
	public HttpResult createOrganization(Organization organization) {
		log.debug("REST REQUEST createOrganization : {} ", organization);
		if (organization.getId() != null) {
			return HttpResult.fail("id 不能有值");
		}

		if (StringUtils.isNotEmpty(organization.getName())) {
			return HttpResult.fail("name 不能为空");
		}

		if (organizationService.getByNameEq(organization.getName()) != null) {
			return HttpResult.fail(organization.getName() + " 已存在");
		}

		organizationService.createOrganization(organization);

		return HttpResult.ok(organization);
	}

	@ApiOperation(value = "删除机构")
	@PostMapping("/delete")
	public HttpResult deleteOrganization(Organization organization){
		log.debug("REST request to delete organization: {}", organization);

		try {
			organizationService.deleteOrganization(organization.getId());
			return HttpResult.ok(null);
		} catch (Exception e){
			return HttpResult.fail("删除失败");
		}
	}
}

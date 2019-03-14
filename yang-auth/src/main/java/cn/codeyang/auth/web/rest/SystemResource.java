package cn.codeyang.auth.web.rest;

import cn.codeyang.auth.entity.System;
import cn.codeyang.auth.service.SystemService;
import cn.codeyang.common.http.utils.HttpResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangzhongyang
 */
@Slf4j
@Api(value = "系统管理", description = "系统管理")
@RestController
@RequestMapping("/api/system")
public class SystemResource {

	@Autowired
	private SystemService systemService;

	@ApiOperation(value = "系统列表")
	@PostMapping("/list")
	public IPage<System> list(Page<System> page, String title, String name) {
		return systemService.query()
				.like(StringUtils.isNotEmpty(title), "title", title)
				.like(StringUtils.isNotEmpty(name), "name", name)
				.orderByDesc("create_time")
				.page(page);
	}


	@ApiOperation(value = "修改系统信息")
	@PostMapping("/update")
	public ResponseEntity<System> updateSystem(@RequestBody System system) {
		log.debug("REST request to update system : {}", system);

		systemService.updateSystem(system);


		return ResponseEntity.ok(null);
	}

	@ApiOperation(value = "新建系统")
	@PostMapping("/create")
	public HttpResult createSystem(@RequestBody System system) {
		log.debug("REST request to create system : {}", system);

		if (system.getId() != null) {
			throw new RuntimeException("id 不能有值");
		} else if (systemService.getOneByName(system.getName()) != null) {
			return HttpResult.fail("name 已存在");
		} else if (systemService.getOneByTitle(system.getTitle()) != null) {
			return HttpResult.fail("title 已存在");
		} else {
			if (systemService.registerSystem(system)) {
				return HttpResult.ok(system);
			}
		}
		return HttpResult.fail("创建失败");
	}

	@ApiOperation(value = "删除系统")
	@PostMapping("/delete")
	public HttpResult deleteSystem(@RequestBody System system){
		log.debug("REST request to delete system: {}", system);

		try {
			systemService.deleteSystem(system.getId());
			return HttpResult.ok(null);
		} catch (Exception e){
			return HttpResult.fail("删除失败");

		}
	}


}
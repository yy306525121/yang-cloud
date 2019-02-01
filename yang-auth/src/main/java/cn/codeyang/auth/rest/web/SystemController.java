package cn.codeyang.auth.rest.web;

import cn.codeyang.auth.entity.System;
import cn.codeyang.auth.entity.enums.BaseStatusEnum;
import cn.codeyang.auth.service.SystemService;
import cn.codeyang.auth.service.dto.SystemDTO;
import cn.codeyang.common.http.utils.HttpResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
@RestController
@RequestMapping("/api/system")
public class SystemController {

	@Autowired
	private SystemService systemService;

	@PostMapping("/list")
	public IPage<System> list(Page<System> page, String title) {
		//return systemService.list();
		return systemService.query()
				.like(StringUtils.isNotEmpty(title), "title", title)
				.orderByDesc("create_time")
				.page(page);
	}


	@PostMapping("/update")
	public ResponseEntity<SystemDTO> updateSystem(@RequestBody SystemDTO systemDTO) {
		log.debug("REST request to update system : {}", systemDTO);

		SystemDTO system = systemService.updateSystem(systemDTO);


		return ResponseEntity.ok(system);
	}

	@PostMapping("/create")
	public HttpResult createSystem(@RequestBody SystemDTO systemDTO) {
		log.debug("REST request to create system : {}", systemDTO);

		if (systemDTO.getId() != null) {
			throw new RuntimeException("id 不能有值");
		} else if (systemService.getOneByName(systemDTO.getName()) != null) {
			return HttpResult.fail("name 已存在");
		} else if (systemService.getOneByTitle(systemDTO.getTitle()) != null) {
			return HttpResult.fail("title 已存在");
		} else {
			System system = new System();
			system.setTitle(systemDTO.getTitle());
			system.setName(systemDTO.getName());
			system.setIcon(systemDTO.getIcon());
			system.setDescription(systemDTO.getDescription());
			system.setBasePath(systemDTO.getBasePath());
			system.setStatus(BaseStatusEnum.NORMAL);
			if (systemService.save(system)) {
				return HttpResult.ok(system);
			}
		}
		return HttpResult.fail("创建失败");
	}

	@PostMapping("/delete")
	public HttpResult deleteSystem(@RequestBody SystemDTO systemDTO){
		log.debug("REST request to delete system: {}", systemDTO);

		try {
			systemService.deleteSystem(systemDTO.getId());
			return HttpResult.ok(null);
		} catch (Exception e){
			return HttpResult.fail("删除失败");
		}
	}


}

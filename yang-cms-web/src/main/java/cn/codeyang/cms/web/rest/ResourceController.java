package cn.codeyang.cms.web.rest;

import cn.codeyang.cms.api.entity.Resource;
import cn.codeyang.cms.api.service.ResourceService;
import cn.codeyang.common.annotation.Inject;
import cn.codeyang.common.http.utils.HttpResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangzhongyang
 */
@Api(value = "多媒体资源")
@RestController
@RequestMapping("/api/resource")
@Slf4j
public class ResourceController {
	@Autowired
	private ResourceService resourceService;


	@PostMapping("/list")
	@ApiOperation(value = "查询所有资源")
	@Inject
	public ResponseEntity<IPage<Resource>> getAll(Page<Resource> pageCondition) {
		IPage<Resource> pageResult = resourceService.query()
				.orderByDesc("create_time")
				.page(pageCondition);

		return ResponseEntity.ok(pageResult);
	}

	@PostMapping("/create")
	public ResponseEntity<HttpResult> create(Resource resource) {
		resourceService.save(resource);

		return ResponseEntity.ok(HttpResult.ok("创建成功"));
	}
}

package cn.codeyang.auth.web.rest;

import cn.codeyang.common.util.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangzhongyang
 */
@RestController
@RequestMapping("/api/token")
@Api(value = "token管理", description = "token管理")
@Slf4j
public class TokenResource {
	@Autowired
	private ConsumerTokenServices consumerTokenServices;

	@ApiOperation(value = "删除token")
	@DeleteMapping("/revoke")
	public void revokeToken() {
		String tokenValue = SecurityUtils.getTokenValue();
		if (StringUtils.isEmpty(tokenValue)){
			log.warn("获取当前用户tokenValue失败");
		}
		consumerTokenServices.revokeToken(tokenValue);
		log.info("注销成功 tokenValue: {}", tokenValue);
	}
}

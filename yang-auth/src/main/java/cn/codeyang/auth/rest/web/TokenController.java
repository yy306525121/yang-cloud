package cn.codeyang.auth.rest.web;

import cn.codeyang.common.util.SecurityUtils;
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
@Slf4j
public class TokenController {
	@Autowired
	private ConsumerTokenServices tokenServices;

	@DeleteMapping("/revoke")
	public void revokeToken() {
		String tokenValue = SecurityUtils.getTokenValue();
		if (StringUtils.isEmpty(tokenValue)){
			log.warn("获取当前用户tokenValue失败");
		}
		tokenServices.revokeToken(tokenValue);
		log.info("注销成功 tokenValue: {}", tokenValue);
	}
}

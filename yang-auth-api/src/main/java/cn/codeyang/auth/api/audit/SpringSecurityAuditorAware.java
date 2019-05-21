package cn.codeyang.auth.api.audit;

import cn.codeyang.auth.api.entity.User;
import cn.codeyang.auth.api.utils.SecurityUtils;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author akafra
 */
@Component
public class SpringSecurityAuditorAware implements AuditorAware<User> {

	@Override
	public Optional<User> getCurrentAuditor() {
		return Optional.of(SecurityUtils.getCurrentUserLogin().orElse(new User()));
	}
}

package cn.codeyang.auth.service.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @author akafra
 */
@Component
public class YangClientDetailsService extends JdbcClientDetailsService {

	public YangClientDetailsService(DataSource dataSource) {
		super(dataSource);
	}

	@Cacheable(cacheNames = "clientCache:clientId", key = "#clientId")
	@Override
	public ClientDetails loadClientByClientId(String clientId) throws InvalidClientException {
		return super.loadClientByClientId(clientId);
	}


}

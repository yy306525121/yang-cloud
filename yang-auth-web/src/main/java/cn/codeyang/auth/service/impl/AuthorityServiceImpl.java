package cn.codeyang.auth.service.impl;


import cn.codeyang.auth.api.service.AuthorityService;
import cn.codeyang.auth.repositories.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yangzhongyang
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {
	@Autowired
	private AuthorityRepository authorityRepository;

}

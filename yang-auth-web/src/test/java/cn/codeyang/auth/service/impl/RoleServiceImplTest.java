package cn.codeyang.auth.service.impl;

import cn.codeyang.auth.api.service.RoleService;
import cn.codeyang.auth.repositories.RoleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author akafra
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class RoleServiceImplTest {

	@Autowired
	private RoleService roleService;
	@Autowired
	private RoleRepository roleRepository;

	@Test
	public void findBookCriteria() {
		//RoleQuery roleQuery = new RoleQuery();
		//roleQuery.setName("ROLE_ADMIN");
		//Page<Role> pageResult = roleService.findBookCriteria(1, 10, roleQuery);
		//System.out.println("Result: " + pageResult);



	}
}

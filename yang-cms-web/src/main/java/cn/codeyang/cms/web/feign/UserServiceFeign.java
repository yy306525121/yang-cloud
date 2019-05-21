package cn.codeyang.cms.web.feign;

import cn.codeyang.auth.api.service.UserService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author akafra
 */
@FeignClient(name = "yang-auth")
public class UserServiceFeign implements UserService {

}

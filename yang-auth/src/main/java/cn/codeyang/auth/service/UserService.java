package cn.codeyang.auth.service;

import cn.codeyang.auth.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author yangzhongyang
 */
public interface UserService extends IService<User>, UserDetailsService {
}

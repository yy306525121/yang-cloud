package cn.codeyang.auth.service.impl;

import cn.codeyang.auth.entity.System;
import cn.codeyang.auth.mapper.SystemMapper;
import cn.codeyang.auth.service.SystemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author yangzhongyang
 */
@Service
public class SystemServiceImpl extends ServiceImpl<SystemMapper, System> implements SystemService {

}

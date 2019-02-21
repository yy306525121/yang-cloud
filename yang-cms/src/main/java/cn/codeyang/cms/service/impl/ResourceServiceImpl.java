package cn.codeyang.cms.service.impl;

import cn.codeyang.cms.entity.Resource;
import cn.codeyang.cms.mapper.ResourceMapper;
import cn.codeyang.cms.service.ResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author yangzhongyang
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {
}

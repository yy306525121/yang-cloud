package cn.codeyang.cms.web.service.impl;

import cn.codeyang.cms.api.entity.Resource;
import cn.codeyang.cms.api.service.ResourceService;
import cn.codeyang.cms.web.mapper.ResourceMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author yangzhongyang
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

}

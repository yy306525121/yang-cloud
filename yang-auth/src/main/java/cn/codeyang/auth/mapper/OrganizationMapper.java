package cn.codeyang.auth.mapper;

import cn.codeyang.auth.entity.Organization;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @author yangzhongyang
 */
@Repository
public interface OrganizationMapper extends BaseMapper<Organization> {

	Organization getByNameEq(String name);
}

package cn.codeyang.auth.mapper;

import cn.codeyang.auth.entity.System;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author yangzhongyang
 */
@Repository
public interface SystemMapper extends BaseMapper<System> {
	System selectOneByName(@Param("name") String name);

	System selectOneByTitle(@Param("title") String title);
}

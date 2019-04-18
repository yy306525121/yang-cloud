package cn.codeyang.cms.web.config.mybatis;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author yangzhongyang
 */
@Configuration
@MapperScan("cn.codeyang.*.web.mapper")
public class MybatisPlusConfiguration {
	/**
	 * 分页
	 *
	 * @return
	 */
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		return new PaginationInterceptor();
	}

	/**
	 * Sql执行效率插件
	 * @return
	 */
	@Bean
	@Profile({"dev", "test"})
	public PerformanceInterceptor performanceInterceptor() {
		return new PerformanceInterceptor();
	}
}

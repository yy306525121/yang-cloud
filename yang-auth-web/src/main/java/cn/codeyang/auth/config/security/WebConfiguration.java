//package cn.codeyang.auth.config.security;
//
//import cn.codeyang.framework.config.YangProperties;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.web.server.WebServerFactory;
//import org.springframework.boot.web.server.WebServerFactoryCustomizer;
//import org.springframework.boot.web.servlet.ServletContextInitializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//
//import javax.servlet.ServletException;
//
///**
// * cors配置
// * @author yangzhongyang
// */
//@Configuration
//@Slf4j
//public class WebConfiguration implements ServletContextInitializer, WebServerFactoryCustomizer<WebServerFactory> {
//	private YangProperties yangProperties;
//
//	public WebConfiguration(YangProperties yangProperties) {
//		this.yangProperties = yangProperties;
//	}
//
//	@Bean
//	public CorsFilter corsFilter() {
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		CorsConfiguration config = yangProperties.getCors();
//
//		config.addAllowedOrigin("*");
//		config.addAllowedHeader("*");
//		config.addAllowedMethod("*");
//		config.setAllowCredentials(true);
//
//		if (config.getAllowedOrigins() != null && !config.getAllowedOrigins().isEmpty()) {
//			log.debug("Registering CORS filter");
//			source.registerCorsConfiguration("/oauth/**", config);
//			source.registerCorsConfiguration("/api/**", config);
//			source.registerCorsConfiguration("/management/**", config);
//			source.registerCorsConfiguration("/v2/api-docs", config);
//		}
//
//		return new CorsFilter(source);
//	}
//
//	public void customize(WebServerFactory factory) {
//
//	}
//
//	public void onStartup(javax.servlet.ServletContext servletContext) throws ServletException {
//
//	}
//}

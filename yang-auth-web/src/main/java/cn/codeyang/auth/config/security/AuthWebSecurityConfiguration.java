//package cn.codeyang.auth.config.security;
//
//import lombok.NoArgsConstructor;
//import org.springframework.beans.factory.BeanInitializationException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
//import org.springframework.security.oauth2.provider.approval.ApprovalStore;
//import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
//
//import javax.annotation.PostConstruct;
//import javax.sql.DataSource;
//
///**
// * @author yangzhongyang
// */
//@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
//@NoArgsConstructor
//public class AuthWebSecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//	@Autowired
//	@Qualifier("userServiceImpl")
//	private UserDetailsService userDetailsService;
//	@Autowired
//	private AuthenticationManagerBuilder authenticationManagerBuilder;
//	@Autowired
//	private DataSource dataSource;
//
//	@PostConstruct
//	public void init() {
//		try {
//			authenticationManagerBuilder
//					.userDetailsService(userDetailsService)
//					.passwordEncoder(passwordEncoder());
//		} catch (Exception e) {
//			throw new BeanInitializationException("Security configuration failed", e);
//		}
//	}
//
//	@Override
//	@Bean
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//		return super.authenticationManagerBean();
//	}
//
//	@Bean
//	public PasswordEncoder passwordEncoder(){
//		return new BCryptPasswordEncoder();
//	}
//
//	@Bean
//	public ApprovalStore approvalStore(){
//		return new JdbcApprovalStore(dataSource);
//	}
//
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		web.ignoring()
//				.antMatchers(HttpMethod.OPTIONS, "/**")
//				.antMatchers("/app/**/*.{js,html}")
//				.antMatchers("/i18n/**")
//				.antMatchers("/content/**")
//				.antMatchers("/swagger-ui/index.html")
//				.antMatchers("/test/**");
//	}
//
//	@Bean
//	public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
//		return new SecurityEvaluationContextExtension();
//	}
//}
//

package cn.codeyang.auth.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author yangzhongyang
 */
@Component
@Slf4j
@WebFilter(urlPatterns = "/*")
public class TimeFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		long start = System.currentTimeMillis();
		chain.doFilter(request, response);
		long end = System.currentTimeMillis();

		String uri = ((HttpServletRequest) request).getRequestURI();

		log.info("uri ====  接口耗时 {}", end - start);
	}

	@Override
	public void destroy() {

	}
}

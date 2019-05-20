package cn.codeyang.auth.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

/**
 * @author yangzhongyang
 */
@ControllerAdvice
@Slf4j
public class GlobalDefultExceptionHandler {

	@ExceptionHandler(IOException.class)
	public String handClientAbortException(){
		return "发生异常";
	}
}

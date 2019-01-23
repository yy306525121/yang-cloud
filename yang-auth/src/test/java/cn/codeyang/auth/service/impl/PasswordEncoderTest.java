package cn.codeyang.auth.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author yangzhongyang
 */
public class PasswordEncoderTest {
	public static void main(String[] args) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hello = passwordEncoder.encode("hello");
		System.out.println(hello);
	}
}

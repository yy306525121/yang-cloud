package cn.codeyang.common.utils;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * 生成随机字符串的工具类
 * @author yangzhongyang
 */
public class RandomUtil {
	public static final int DEF_COUNT = 20;

	/**
	 * 生成激活码
	 * @return 激活码
	 */
	public static String generateActivationKey() {
		return RandomStringUtils.randomNumeric(DEF_COUNT);
	}

	/**
	 * 生成密码重置码
	 * @return 重置码
	 */
	public static String generateResetKey(){
		return RandomStringUtils.randomNumeric(DEF_COUNT);
	}
}

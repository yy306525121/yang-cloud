package cn.codeyang.auth.service;

import cn.codeyang.auth.entity.User;

/**
 * 邮件服务类
 * @author yangzhongyang
 */
public interface MailService {
	void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml);

	void sendEmailFromTemplate(User user, String templateName, String titleKey);

	void sendActivationEmail(User user);

	void sendCreationEmail(User user);

	void sendPasswordResetMail(User user);
}

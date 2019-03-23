package cn.codeyang.auth.service.impl;

import cn.codeyang.auth.api.entity.User;
import cn.codeyang.auth.api.service.MailService;
import cn.codeyang.common.config.YangProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

/**
 * @author yangzhongyang
 */
@Service
@Slf4j
public class MailServiceImpl implements MailService {

	public static final String USER = "user";
	public static final String BASE_URL = "baseUrl";

	private final JavaMailSender javaMailSender;
	private final MessageSource messageSource;
	private final SpringTemplateEngine templateEngine;
	private final YangProperties yangProperties;


	public MailServiceImpl(JavaMailSender javaMailSender, MessageSource messageSource, SpringTemplateEngine templateEngine, YangProperties yangProperties) {
		this.javaMailSender = javaMailSender;
		this.messageSource = messageSource;
		this.templateEngine = templateEngine;
		this.yangProperties = yangProperties;
	}

	@Override
	public void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml) {
		if (log.isDebugEnabled()) {
			log.debug("Send email[isMultipart '{}' and isHtml '{}'] to '{}' with subject '{}' and content = {}",
					isMultipart, isHtml, to, subject, content);
		}

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();

		try {
			MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart, StandardCharsets.UTF_8.name());
			message.setTo(to);
			message.setFrom(yangProperties.getMail().getFrom());
			message.setSubject(subject);
			message.setText(content, isHtml);
			javaMailSender.send(mimeMessage);

			if (log.isDebugEnabled()) {
				log.debug("Sent email to User '{}'", to);
			}
		} catch (MessagingException e) {
			if (log.isDebugEnabled()) {
				log.warn("email could not be sent to user '{}'", to);
			} else {
				log.warn("email could not be sent to user '{}': {}", to, e.getMessage());
			}
		}
	}

	@Override
	public void sendEmailFromTemplate(User user, String templateName, String titleKey) {
		Locale locale = Locale.forLanguageTag(user.getLangKey());
		Context context = new Context(locale);
		context.setVariable(USER, user);
		context.setVariable(BASE_URL, yangProperties.getMail().getBaseUrl());
		String content = templateEngine.process(templateName, context);
		String subject = messageSource.getMessage(titleKey, null, locale);

		sendEmail(user.getEmail(), subject, content, false, true);
	}

	@Override
	public void sendActivationEmail(User user) {
		log.debug("发送激活邮件到 '{}'", user.getEmail());
		sendEmailFromTemplate(user, "mail/activationEmail", "email.activation.title");
	}

	@Override
	public void sendCreationEmail(User user) {
		if (log.isDebugEnabled()) {
			log.debug("Sending activation email to '{}'", user.getEmail());
		}
		sendEmailFromTemplate(user, "mail/creationEmail", "email.activation.title");
	}

	@Override
	public void sendPasswordResetMail(User user) {
		log.debug("Sending password reset email to '{}'", user.getEmail());
		sendEmailFromTemplate(user, "mail/passwordResetEmail", "email.reset.title");
	}
}

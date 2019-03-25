package cn.codeyang.auth.component;

import cn.codeyang.auth.api.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author yangzhongyang
 */
@Component
@Slf4j
public class RabbitMQSender {
	private RabbitTemplate rabbitTemplate;

	public RabbitMQSender(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	/**
	 * 消息发送成功后回调
	 */
	private final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
		@Override
		public void confirm(CorrelationData correlationData, boolean ack, String cause) {
			log.debug("消息投递成功");
			if (!ack) {
				log.error("send message failed: " + cause);
				throw new RuntimeException("send error " + cause);
			} else {
				log.info("消息发送成功: 消息ID： " + (correlationData != null ? correlationData.getId() : null) );
			}
		}
	};

	/**
	 * 对详细投递失败进行处理
	 */
	private final RabbitTemplate.ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback(){
		@Override
		public void returnedMessage(org.springframework.amqp.core.Message message, int replyCode, String replyText, String exchange, String routingKey) {
			log.error("消息投递失败");
		}
	};


	/**
	 * 发送消息
	 * @param user
	 * @param properties
	 */
	public void sendEmail(User user, Map<String, Object> properties) {
		log.info("发送Email ： " + user);
		MessageHeaders messageHeaders = new MessageHeaders(properties);
		Message<Object> message = MessageBuilder.createMessage(user, messageHeaders);
		rabbitTemplate.setReturnCallback(returnCallback);
		rabbitTemplate.setConfirmCallback(confirmCallback);
		rabbitTemplate.convertAndSend("yang-cloud.email", "email.sender", message, new CorrelationData(user.getId().toString()));
	}


}

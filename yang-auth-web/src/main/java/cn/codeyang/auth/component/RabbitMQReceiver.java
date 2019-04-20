//package cn.codeyang.auth.component;
//
//import cn.codeyang.auth.api.entity.User;
//import cn.codeyang.auth.api.service.MailService;
//import com.rabbitmq.client.Channel;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.rabbit.annotation.*;
//import org.springframework.amqp.support.AmqpHeaders;
//import org.springframework.messaging.handler.annotation.Headers;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.util.Map;
//
///**
// * @author yangzhongyang
// */
//@Component
//@Slf4j
//public class RabbitMQReceiver {
//
//	private final MailService mailService;
//
//	public RabbitMQReceiver(MailService mailService) {
//		this.mailService = mailService;
//	}
//
//	@RabbitListener(bindings = @QueueBinding(
//			value = @Queue(value = "yang-cloud.email", durable = "false"),
//			exchange = @Exchange(value = "yang-cloud.email", type = "topic", ignoreDeclarationExceptions = "true"),
//			key = "email.*"))
//	@RabbitHandler
//	public void sendEmail(@Payload User user, Channel channel, @Headers Map<String, Object> headers) throws IOException {
//		log.info("Receive RabbitMQ message: " + user);
//
//		mailService.sendActivationEmail(user);
//		Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
//		channel.basicAck(deliveryTag, false);
//	}
//}

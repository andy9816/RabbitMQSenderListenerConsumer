package io.spring.rabbitMQ.config;

import io.spring.rabbitMQ.model.ManuOrder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {

    @Value("${rabbitmq.exchange}")
    private String exchange;
    @Value("${rabbitmq.queue")
    private String queueName;
    @Autowired
    private AmqpTemplate rabbitTemplate;
//    @Autowired
//    private Queue queue;
    private static Logger logger = LogManager.getLogger(RabbitMQSender.class.toString());
    public void send(String routingkey, ManuOrder manuOrder) {
        rabbitTemplate.convertAndSend(exchange, routingkey, manuOrder);
        logger.info("Sending Message to the Queue : " + manuOrder.toString());
    }
}

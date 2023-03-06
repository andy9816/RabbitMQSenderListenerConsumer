package io.spring.rabbitMQ.config;

import io.spring.rabbitMQ.model.ManuOrder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "q1", id = "listener")
public class RabbitMQReceiver {

    private static Logger logger = LogManager.getLogger(RabbitMQReceiver.class.toString());
    @RabbitHandler
    public void receiver(ManuOrder manuOrder){

        logger.info("MenuOrder listner invoked -Consuming message with ManuOrder Indentifier : " + manuOrder.getOrderIdentifier());
    }
//    @RabbitHandler
//    public void receiver()
}

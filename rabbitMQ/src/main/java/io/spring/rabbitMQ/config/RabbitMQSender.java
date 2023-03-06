package io.spring.rabbitMQ.config;

import io.spring.rabbitMQ.model.ManuOrder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

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
    public void send(String routingKey, ManuOrder manuOrder) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(exchange, routingKey, manuOrder);
        try {
            boolean confirmed = correlationData.getFuture().get(10, TimeUnit.SECONDS).isAck();
            if(confirmed){
                System.out.println("Topic= "+manuOrder.getOrderId()+"Sent message=" + manuOrder + " with offset=" + manuOrder.getCustomerName());
            }
            else {
                System.out.println("Failed to send message due to " + manuOrder);
            }
        }
        catch (TimeoutException e) {
            System.out.println("Timeout while waiting for confirmation");
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted while waiting for confirmation");
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            System.out.println("Exception while waiting for confirmation: " + e.getMessage());
        }
        logger.info("Sending Message to the Queue : " + manuOrder.toString());

    }

    public void send(String routingKey, String message){
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
        try {
            boolean confirmed = correlationData.getFuture().get(10, TimeUnit.SECONDS).isAck();
            if(confirmed){
                System.out.println("Sent message=" + message + " with offset=");
            }
            else {
                System.out.println("Failed to send message due to " + message);
            }
        }
        catch (TimeoutException e) {
            System.out.println("Timeout while waiting for confirmation");
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted while waiting for confirmation");
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            System.out.println("Exception while waiting for confirmation: " + e.getMessage());
        }
    }
}

package io.spring.rabbitMQ.contoller;

import io.spring.rabbitMQ.config.RabbitMQSender;
import io.spring.rabbitMQ.model.ManuOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitController {
    @Autowired
    RabbitMQSender rabbitMQSender;
    @PostMapping(value = "/sender")
    public String producer(@RequestBody ManuOrder menuOrder) {
        rabbitMQSender.send("abc", menuOrder);
        return "Message sent to the RabbitMQ Queue Successfully";
    }
}

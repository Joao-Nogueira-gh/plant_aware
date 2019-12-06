package ua.deti.plant_aware;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import org.springframework.stereotype.Component;

import ua.deti.plant_aware.repository.PlantRepository;

/**
 * 
 * Message Queue
 * Receives messages of type
 * {
 *  "id": 1,
 *  "soil": ...,
 *  "temp": ...,
 *  "wind": ...,
 *  "timestamp": ...
 * }
 * 
 * 
 * 
 */

@Component
public class MQ {

    private final String QUEUE_NAME = "plants_info";

    public MQ(PlantRepository rep) throws Exception{

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("rabbit");
        factory.setPort(5672);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {

            // Receiving message
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");

            rep.handle(message);

        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });

    }
}

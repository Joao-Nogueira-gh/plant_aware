package ua.deti.plant_aware;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import org.springframework.stereotype.Component;
import java.util.*;

import java.lang.Integer;

import ua.deti.plant_aware.repository.PlantRepository;
import ua.deti.plant_aware.model.*;
import ua.deti.plant_aware.util.*;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import org.springframework.data.mongodb.core.query.Query;

@Component
public class MQ {

    private final String QUEUE_NAME = "plants_info";

    public MQ(PlantRepository rep) throws Exception{

        String plant_name = "Tulipa";

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");

            HashMap<String, Object> hm = new HashMap<>(Utility.jsonToMap(message));
            System.out.println(hm);
            Plant plant = new Plant(plant_name);

            plant.setSoil((double) hm.get("soil"));
            plant.setTemp((double) hm.get("temp"));
            plant.setWind((int) hm.get("wind"));
            plant.setId(((Integer) hm.get("plant_id")).longValue());


            if(!rep.exists(new Query(where("plant_id").is(1)), "main"))
            {
                System.out.println("Saving " + rep.insert(plant));
            }
            else
            {
                System.out.println("ALREADY EXISTS");
            }

        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });

    }
}

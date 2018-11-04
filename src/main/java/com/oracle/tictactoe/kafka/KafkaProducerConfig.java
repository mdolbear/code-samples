package com.oracle.tictactoe.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
public class KafkaProducerConfig {


    /**
     * Create and answer a kafka producer factory
     * @return ProducerFactory
     */
    @Bean
    public ProducerFactory<String, EventObject>
                            producerFactory(KafkaProperties aProperties) {

        Map<String, Object> tempConfigProperties =
                    new HashMap();

        tempConfigProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                                 aProperties.getBootstrapServers());
        tempConfigProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                                 StringSerializer.class);
        tempConfigProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                                 EventSerializer.class);

        return new DefaultKafkaProducerFactory<>(tempConfigProperties);

    }

    /**
     * Create and answer a kafka template
     * @param producerFactory Map
     */
    @Bean
    public KafkaTemplate<String,EventObject>
                        kafkaTemplate(ProducerFactory<String,EventObject> producerFactory) {

        return new KafkaTemplate<String,EventObject>(producerFactory);
    }

}

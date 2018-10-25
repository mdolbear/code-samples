package com.oracle.tictactoe.kafka;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Component
public class KafkaProperties {


    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PRIVATE)
    private String bootstrapServers;

    /**
     * Answer an instance of me on the following
     * @param bootstrapServers String
     */
    @Autowired
    public KafkaProperties(@Value("#{'${bootstrap-servers}'}")String bootstrapServers) {

        super();
        this.setBootstrapServers(bootstrapServers);
    }

}

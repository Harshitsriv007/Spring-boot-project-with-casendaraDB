package com.examplecasandra.demo.config;

import com.datastax.oss.driver.api.core.CqlSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.net.InetSocketAddress;

@Configuration
public class CassandraKeyspaceInitializer {

    @Value("${spring.data.cassandra.keyspace-name}")
    private String keyspace;

    @Value("${spring.data.cassandra.contact-points}")
    private String contactPoint;

    @Value("${spring.data.cassandra.port}")
    private int port;

    @PostConstruct
    public void initKeyspace() {
        try (CqlSession session = CqlSession.builder()
                .addContactPoint(new InetSocketAddress(contactPoint, port))
                .withLocalDatacenter("datacenter1")
                .build()) {

            String createKs = "CREATE KEYSPACE IF NOT EXISTS " + keyspace +
                    " WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1}";
            session.execute(createKs);
        }
    }
}


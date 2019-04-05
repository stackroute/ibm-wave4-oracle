package com.stackroute.graphquery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jRepositories
public class GraphQueryApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraphQueryApplication.class, args);
    }

}

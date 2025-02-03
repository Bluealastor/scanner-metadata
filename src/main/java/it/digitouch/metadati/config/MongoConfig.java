package it.digitouch.metadati.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;

public class MongoConfig {
    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;

    @PostConstruct
    public void printMongoUri() {
        System.out.println("Mongo URI: " + mongoUri);
    }
}

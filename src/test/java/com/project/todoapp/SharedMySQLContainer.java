package com.project.todoapp;

import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

public class SharedMySQLContainer extends MySQLContainer<SharedMySQLContainer> {
    private static final DockerImageName IMAGE_NAME = DockerImageName.parse("mysql:8.0");
    private static volatile SharedMySQLContainer sharedMySQLContainer;

    public SharedMySQLContainer(DockerImageName dockerImageName) {
        super(dockerImageName);
        this.withReuse(true)
            .withDatabaseName("testdb")
            .withUsername("name")
            .withPassword("password");
    }

    public static SharedMySQLContainer getInstance() {
        if (sharedMySQLContainer == null) {
            synchronized (SharedMySQLContainer.class) {
                sharedMySQLContainer = new SharedMySQLContainer(IMAGE_NAME);
                sharedMySQLContainer.start();
            }
        }
        return sharedMySQLContainer;
    }
}

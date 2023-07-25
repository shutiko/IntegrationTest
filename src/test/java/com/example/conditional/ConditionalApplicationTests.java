package com.example.conditional;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConditionalApplicationTests {
    private static final GenericContainer myapp1 = new GenericContainer("devapp")
            .withExposedPorts(8080);

    private static final GenericContainer myapp2 = new GenericContainer("prodapp")
            .withExposedPorts(8081);

    @Autowired
    private TestRestTemplate restTemplate;


    @BeforeAll
    static void setUp() {
        myapp1.start();
        myapp2.start();
    }

    @Test
    public void contextLoads() {
        ResponseEntity<String> forEntity1 = restTemplate.getForEntity("http://localhost:" + myapp1.getMappedPort(8080) + "/profile", String.class);
//        Integer mappedPort8080 = myapp1.getMappedPort(8080);
        System.out.println(forEntity1.getBody());
        String resultDev = forEntity1.getBody();

        Assertions.assertEquals("Current profile is dev", resultDev);


        ResponseEntity<String> forEntity2 = restTemplate.getForEntity("http://localhost:" + myapp2.getMappedPort(8081) + "/profile", String.class);
//        Integer mappedPort8081 = myapp2.getMappedPort(8081);
        System.out.println(forEntity2.getBody());
        String resultProd = forEntity2.getBody();

        Assertions.assertEquals("Current profile is production", resultProd);

    }

}

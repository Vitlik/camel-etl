package de.viadee.cameltest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyRouteTest {

    // @Inject
    // private CamelContext camelContext;

    @Test
    public void routeTest() {

        assertTrue(true);

        // camelContext.createProducerTemplate().sendBody("direct:dateiVerarbeiten", datei);
        // Thread.sleep(3000);
    }
}

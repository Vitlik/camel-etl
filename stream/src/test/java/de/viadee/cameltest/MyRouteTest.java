package de.viadee.cameltest;

import static org.junit.Assert.assertTrue;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.camel.CamelContext;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import de.viadee.cameltest.Entities.Target.dim_supplier;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyRouteTest {

    private static final Logger LOGGER = Logger.getLogger(MyRouteTest.class);

    @Inject
    private CamelContext camelContext;

    @Inject
    private EntityManager em;

    @Inject
    private Loader<dim_supplier> test;

    @Test
    public void routeTest() {

        LOGGER.info(em.getClass());

        assertTrue(true);

        // camelContext.createProducerTemplate().sendBody("direct:dateiVerarbeiten", datei);
        // Thread.sleep(3000);
    }
}

package de.viadee.cameltest;

import org.apache.camel.builder.RouteBuilder;
import org.apache.log4j.Logger;

// @Component
public class MyRoute extends RouteBuilder {

    private static final Logger LOGGER = Logger.getLogger(MyRoute.class);

    // @Inject
    // private MappToWarehouse mappToWarehouse;
    //
    // @Inject
    // private WriteToDB writeToDB;

    @Override
    public void configure() throws Exception {
        LOGGER.info("Route gestartet.");

        from("jpa:de.viadee.cameltest.Entities.Source.warehouse_and_retail_sales"
                + "?consumer.initialDelay=1000"
        // + "&maxMessagesPerPoll=10"

        // + "&persistenceUnit=packagesToScan"

        // + "&maximumResults=1000"

        // + "&consumeDelete=false"

        )

                .routeId("sales-dim-mapping")
                // .process(mappToWarehouse)
                // .to("direct:toPersisting");
                //
                // from("direct:toPersisting")
                // .process(writeToDB)
                // .to("jpa:de.viadee.cameltest.Entities.Target.dim_supplier") // some problems with EntityManager camel

                .to("log:foo");
    }
}

package de.viadee.cameltest;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class Application {

    @Bean
    public CamelContext context(MyRoute myRoute) throws Exception {
        CamelContext context = new DefaultCamelContext();
        context.addRoutes(myRoute);
        context.start();
        Thread.sleep(10000);
        return context;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Component
    public class MyRoute extends RouteBuilder {

        // @Inject
        // private MappToWarehouse mappToWarehouse;
        //
        // @Inject
        // private WriteToDB writeToDB;

        @Override
        public void configure() throws Exception {

            from("jpa:de.viadee.cameltest.Entities.Source.warehouse_and_retail_sales"
                    + "?consumer.initialDelay=1000"
            // + "&consumer.namedQuery=new-orders"
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
                    // .to("jpa:de.viadee.cameltest.Entities.Target.dim_supplier") // some problems with EntityManager
                    // camel

                    .to("log:foo");
        }
    }
}

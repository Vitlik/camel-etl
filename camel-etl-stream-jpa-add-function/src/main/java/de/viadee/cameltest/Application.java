package de.viadee.cameltest;

import javax.inject.Inject;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.stereotype.Component;

import de.viadee.cameltest.Processes.CleanData;
import de.viadee.cameltest.Processes.DateDimProcess;
import de.viadee.cameltest.Processes.ItemDimProcess;
import de.viadee.cameltest.Processes.MappToWarehouse;
import de.viadee.cameltest.Processes.SupplierDimProcess;
import de.viadee.cameltest.Processes.WriteFacts;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Component
    public class MyRoute extends RouteBuilder {

        @Inject
        private MappToWarehouse mapToWarehouse;

        @Inject
        private CleanData cleanData;

        @Inject
        private DateDimProcess dateDimProcess;

        @Inject
        private ItemDimProcess itemDimProcess;

        @Inject
        private SupplierDimProcess supplierDimProcess;

        @Inject
        private WriteFacts writeFacts;

        @Override
        public void configure() throws Exception {

            from("jpa:de.viadee.cameltest.Entities.Source.WarehouseAndRetailSales"
                    + "?consumeLockEntity=false"
                    + "&maxMessagesPerPoll=50"
            // + "&consumeDelete=false"
            )
                    .routeId("sales-dim-mapping")
                    .log("Started route.")

                    .process(mapToWarehouse)
                    .process(cleanData)
                    .process(dateDimProcess)
                    .process(itemDimProcess)
                    .process(supplierDimProcess)
                    .process(writeFacts)

                    // .log("Processed ${body.year} ${body.month} ${body.supplier} ${body.itemCode}
                    // ${body.itemType} ${body.itemDescription} ")
                    .log("Finished route.");
        }
    }
}
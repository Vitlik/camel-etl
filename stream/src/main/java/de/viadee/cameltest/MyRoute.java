package de.viadee.cameltest;

import javax.inject.Inject;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import de.viadee.cameltest.Entities.Source.Warehouse_and_Retail_Sales;
import de.viadee.cameltest.Processes.MappToWarehouse;
import de.viadee.cameltest.Processes.WriteToDB;

@Component
public class MyRoute extends RouteBuilder {

    private static final Logger LOGGER = Logger.getLogger(MyRoute.class);

    @Inject
    private MappToWarehouse mappToWarehouse;

    @Inject
    private WriteToDB writeToDB;

    @Override
    public void configure() throws Exception {
        LOGGER.info("Route fuer die Cat-Auftragsgenerierung wird konfiguriert.");

        BindyCsvDataFormat bindy = new BindyCsvDataFormat(Warehouse_and_Retail_Sales.class);

        from("file:C:\\Projects\\data?fileName=Warehouse_and_Retail_Sales.csv&noop=true")
                .unmarshal(bindy)
                .process(mappToWarehouse)
                // .to("direct:toPersisting");
                //
                // from("direct:toPersisting")
                // .process(writeToDB);
                .to("jpa:de.viadee.cameltest.Entities.Target.dim_supplier"); // some problems with EntityManager camel
    }
}

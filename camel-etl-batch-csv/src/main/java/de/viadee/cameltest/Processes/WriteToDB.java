package de.viadee.cameltest.Processes;

import java.util.List;

import javax.inject.Inject;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import de.viadee.cameltest.Loader;
import de.viadee.cameltest.Entities.Target.dim_date;
import de.viadee.cameltest.Entities.Target.dim_item;
import de.viadee.cameltest.Entities.Target.dim_supplier;
import de.viadee.cameltest.Entities.Target.fact_sales;

@Component
public class WriteToDB implements Processor {

    private static final Logger LOGGER = Logger.getLogger(WriteToDB.class);

    @Inject
    private Loader<dim_date> dateLoader;

    @Inject
    private Loader<dim_item> itemLoader;

    @Inject
    private Loader<dim_supplier> supplierLoader;

    @Inject
    private Loader<fact_sales> factLoader;

    @SuppressWarnings("unchecked")
    @Override
    public void process(Exchange exchange) throws Exception {

        LOGGER.info("--- Write to DB ---");

        List<Object> objectList = (List<Object>) exchange.getIn().getBody();
        List<fact_sales> fact_List = (List<fact_sales>) objectList.get(0);
        List<dim_date> dimDateList = (List<dim_date>) objectList.get(1);
        List<dim_item> dimItemList = (List<dim_item>) objectList.get(2);
        List<dim_supplier> dimSupplierList = (List<dim_supplier>) objectList.get(3);

        dimDateList.forEach(elem -> {
            dateLoader.load(elem);
        });

        dimItemList.forEach(elem -> {
            itemLoader.load(elem);
        });

        dimSupplierList.forEach(item -> {
            supplierLoader.load(item);
        });

        fact_List.forEach(item -> {
            factLoader.load(item);
        });
    }
}

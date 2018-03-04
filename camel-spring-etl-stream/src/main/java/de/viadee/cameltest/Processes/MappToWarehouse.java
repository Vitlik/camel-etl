package de.viadee.cameltest.Processes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import de.viadee.cameltest.Entities.Source.warehouse_and_retail_sales;
import de.viadee.cameltest.Entities.intermediate.fullDataWithIDs;

@Component
public class MappToWarehouse implements Processor {

    private static final Logger LOGGER = Logger.getLogger(MappToWarehouse.class);

    @Override
    public void process(Exchange exchange) throws Exception {

        warehouse_and_retail_sales rawSale = exchange.getIn().getBody(warehouse_and_retail_sales.class);

        fullDataWithIDs idData = new fullDataWithIDs();

        BeanUtils.copyProperties(rawSale, idData);

        exchange.getOut().setBody(idData);

    }
}

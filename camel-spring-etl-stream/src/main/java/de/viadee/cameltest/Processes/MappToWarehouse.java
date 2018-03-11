package de.viadee.cameltest.Processes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import de.viadee.cameltest.Entities.Source.WarehouseAndRetailSales;
import de.viadee.cameltest.Entities.intermediate.FullDataWithIds;

@Component
public class MappToWarehouse implements Processor {

    private static final Logger LOGGER = Logger.getLogger(MappToWarehouse.class);

    @Override
    public void process(Exchange exchange) throws Exception {

        WarehouseAndRetailSales rawSale = exchange.getIn().getBody(WarehouseAndRetailSales.class);

        FullDataWithIds idData = new FullDataWithIds();

        BeanUtils.copyProperties(rawSale, idData);

        exchange.getOut().setBody(idData);

    }
}

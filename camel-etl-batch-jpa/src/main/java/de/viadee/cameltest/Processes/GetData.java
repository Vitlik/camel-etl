package de.viadee.cameltest.Processes;

import java.util.List;

import javax.inject.Inject;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import de.viadee.cameltest.Entities.Source.WarehouseAndRetailSales;
import de.viadee.cameltest.Entities.Source.Repos.WarehouseAndRetailSalesRepository;

@Component
public class GetData implements Processor {

    @Inject
    private WarehouseAndRetailSalesRepository salesRepo;

    @Override
    public void process(Exchange exchange) {

        List<WarehouseAndRetailSales> rawData = salesRepo.findAll();

        exchange.getOut().setBody(rawData);
    }

}

package de.viadee.cameltest.Processes;

import javax.inject.Inject;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import de.viadee.cameltest.Entities.Target.FactSales;
import de.viadee.cameltest.Entities.Target.Repos.FactSalesRepository;
import de.viadee.cameltest.Entities.intermediate.FullDataWithIds;

@Component
public class WriteFacts implements Processor {

    private static final Logger LOGGER = Logger.getLogger(WriteFacts.class);

    @Inject
    private FactSalesRepository factRepo;

    @Override
    public void process(Exchange exchange) {

        FullDataWithIds saleWithIds = exchange.getIn().getBody(FullDataWithIds.class);

        FactSales factDataDB = factRepo.findByDateIdAndSupplierIdAndItemId(saleWithIds.getDateId(),
                saleWithIds.getSupplierId(), saleWithIds.getItemId());

        if (factDataDB == null) {
            FactSales factData = new FactSales();

            BeanUtils.copyProperties(saleWithIds, factData);

            factRepo.saveAndFlush(factData);

        } else {
            LOGGER.debug("Fact already existing. ");
        }
    }
}

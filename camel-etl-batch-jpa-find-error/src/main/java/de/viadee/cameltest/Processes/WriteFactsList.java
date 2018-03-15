package de.viadee.cameltest.Processes;

import java.util.List;

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
public class WriteFactsList implements Processor {

    private static final Logger LOGGER = Logger.getLogger(WriteFactsList.class);

    @Inject
    private FactSalesRepository factRepo;

    @Override
    public void process(Exchange exchange) {

        @SuppressWarnings("unchecked")
        List<FullDataWithIds> dataList = (List<FullDataWithIds>) exchange.getIn().getBody();

        for (FullDataWithIds saleWithIds : dataList) {

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
}

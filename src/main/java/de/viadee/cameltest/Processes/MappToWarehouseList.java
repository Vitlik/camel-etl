package de.viadee.cameltest.Processes;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import de.viadee.cameltest.Entities.Source.WarehouseAndRetailSales;
import de.viadee.cameltest.Entities.intermediate.FullDataWithIds;

@Component
public class MappToWarehouseList implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

        @SuppressWarnings("unchecked")
        List<WarehouseAndRetailSales> rawDataList = (List<WarehouseAndRetailSales>) exchange.getIn().getBody();

        List<FullDataWithIds> idDataList = new ArrayList<FullDataWithIds>();

        for (WarehouseAndRetailSales rawSale : rawDataList) {

            FullDataWithIds idData = new FullDataWithIds();

            BeanUtils.copyProperties(rawSale, idData);

            idDataList.add(idData);
        }

        exchange.getOut().setBody(idDataList);

    }
}

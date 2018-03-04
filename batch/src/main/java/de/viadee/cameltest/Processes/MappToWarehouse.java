package de.viadee.cameltest.Processes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import de.viadee.cameltest.Loader;
import de.viadee.cameltest.Entities.Source.Warehouse_and_Retail_Sales;
import de.viadee.cameltest.Entities.Target.dim_date;
import de.viadee.cameltest.Entities.Target.dim_item;
import de.viadee.cameltest.Entities.Target.dim_supplier;
import de.viadee.cameltest.Entities.Target.fact_sales;
import de.viadee.cameltest.Entities.intermediate.fullDataWithIDs;

@Component
public class MappToWarehouse implements Processor {

    private static final Logger LOGGER = Logger.getLogger(MappToWarehouse.class);

    @Inject
    private Loader<dim_item> itemLoader;

    @Override
    public void process(Exchange exchange) throws Exception {

        LOGGER.info("--- Execute Dimension Mapping ---");

        @SuppressWarnings("unchecked")
        List<Warehouse_and_Retail_Sales> csvList = (List<Warehouse_and_Retail_Sales>) exchange
                .getIn().getBody();

        List<fullDataWithIDs> fullDataList = new ArrayList<>();

        csvList.forEach(row -> {
            fullDataWithIDs newRow = new fullDataWithIDs();
            BeanUtils.copyProperties(row, newRow);
            fullDataList.add(newRow);
        });

        List<dim_date> dimDateList = new ArrayList<dim_date>();
        List<dim_item> dimItemList = new ArrayList<dim_item>();
        List<dim_supplier> dimSupplierList = new ArrayList<dim_supplier>();
        List<fact_sales> factSalesList = new ArrayList<fact_sales>();

        fullDataList.forEach(row -> {
            extractDate(row, dimDateList);
            extractItem(row, dimItemList);
            extractSupplier(row, dimSupplierList);
        });

        fullDataList.forEach(row -> {
            fact_sales newRow = new fact_sales();
            BeanUtils.copyProperties(row, newRow);
            factSalesList.add(newRow);
        });

        List<Object> newMessage = new ArrayList<Object>(
                Arrays.asList(factSalesList, dimDateList, dimItemList, dimSupplierList));
        exchange.getIn().setBody(newMessage);
        // exchange.getIn().setBody(dimSupplierList);

    }

    public void extractDate(fullDataWithIDs row, List<dim_date> dimDateList) {
        for (dim_date dimRow : dimDateList) {
            if ((row.getYear() + "" + row.getMonth()).equals(dimRow.getYear() + "" + dimRow.getMonth())) {
                row.setDate_id((int) dimRow.getId());
                return;
            }
        }
        ;
        dim_date newElem = new dim_date(dimDateList.size() + 1, row.year, row.month, null);
        dimDateList.add(newElem);
        row.setDate_id((int) newElem.getId());
    }

    public void extractItem(fullDataWithIDs row, List<dim_item> dimItemList) {
        for (dim_item dimRow : dimItemList) {
            if ((row.getItem_type() + "" + row.getItem_code() + "" + row.getItem_description())
                    .equals(dimRow.getType() + "" + dimRow.getCode() + "" + dimRow.getDescription())) {
                row.setItem_id((int) dimRow.getId());
                return;
            }
        }
        ;
        dim_item newElem = new dim_item(dimItemList.size() + 1, row.getItem_type(), row.getItem_code(),
                row.getItem_description());
        dimItemList.add(newElem);
        row.setItem_id((int) newElem.getId());

    }

    public void extractSupplier(fullDataWithIDs row, List<dim_supplier> dimSupplierList) {
        for (dim_supplier dimRow : dimSupplierList) {
            if ((row.getSupplier()).equals(dimRow.getName())) {
                row.setSupplier_id((int) dimRow.getId());
                return;
            }
        }
        ;
        dim_supplier newElem = new dim_supplier(dimSupplierList.size() + 1, row.getSupplier());
        dimSupplierList.add(newElem);
        row.setSupplier_id((int) newElem.getId());
    }
}

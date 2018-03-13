package de.viadee.cameltest.Methods;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.viadee.cameltest.Entities.AggregatedData;
import de.viadee.cameltest.Entities.CsvData;

public class AggregatorTest {

    @Test
    public void aggregatorTest() {

        @SuppressWarnings("rawtypes")
        Aggregator aggr = new Aggregator();

        List<CsvData> sourceObjectList = new ArrayList<CsvData>();

        sourceObjectList.add(new CsvData(2012, 4, "mySupplier", "myCode", "myDesc", "myType1", 1.00, 2.00, 3.00));
        sourceObjectList.add(new CsvData(2012, 4, "mySupplier2", "myCode", "myDesc", "myType1", 2.00, 3.00, 0.00));
        sourceObjectList.add(new CsvData(2012, 4, "mySupplier3", "myCode", "myDesc", "myType1", 3.00, 7.00, 0.00));

        sourceObjectList.add(new CsvData(2012, 5, "mySupplier3", "myCode", "myDesc", "myType1", 2.00, 3.00, 0.00));
        sourceObjectList.add(new CsvData(2012, 5, "mySupplier", "myCode", "myDesc", "myType1", 2.00, 3.00, 0.00));
        sourceObjectList.add(new CsvData(2012, 5, "mySupplier", "myCode", "myDesc", "myType1", 3.00, 3.00, 10.00));

        sourceObjectList.add(new CsvData(2012, 4, "mySupplier2", "myCode", "myDesc", "myType2", 2.00, 3.00, 0.00));
        sourceObjectList.add(new CsvData(2012, 4, "mySupplier3", "myCode", "myDesc", "myType2", 3.00, 2.00, 0.00));

        try {
            @SuppressWarnings("unchecked")
            List<AggregatedData> myAggregatedData = (List<AggregatedData>) aggr
                    .setSource(sourceObjectList)
                    .setTarget(AggregatedData.class.getName())
                    .groupBy("year").groupBy("month").groupBy("itemType")
                    .setAttribute("supplier").setOperation("first").setAttributeTarget("supplier")
                    .setAttribute("retailSales").setOperation("sum").setAttributeTarget("retailSalesSum")
                    .setAttribute("retailTransfers").setOperation("avg").setAttributeTarget("retailTransfersAvg")
                    .setAttribute("warehouseSales").setOperation("max").setAttributeTarget("warehouseSalesMax")
                    .run();

            assertEquals(myAggregatedData.size(), 3);

            // first group
            AggregatedData data1 = new AggregatedData(2012, 4, "mySupplier", "myType1", 6.00, 4.00, 3.00);
            // second group
            AggregatedData data2 = new AggregatedData(2012, 5, "mySupplier3", "myType1", 7.00, 3.00, 10.00);
            // third group
            AggregatedData data3 = new AggregatedData(2012, 4, "mySupplier2", "myType2", 5.00, 2.50, 0);

            assertThat(myAggregatedData, hasItems(data1));
            assertThat(myAggregatedData, hasItems(data2));
            assertThat(myAggregatedData, hasItems(data3));

        } catch (SecurityException e) {
            throw new RuntimeException(e);
        }
    }
}

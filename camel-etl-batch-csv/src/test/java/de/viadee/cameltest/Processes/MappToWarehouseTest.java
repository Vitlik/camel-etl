package de.viadee.cameltest.Processes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import de.viadee.cameltest.Entities.Target.dim_date;
import de.viadee.cameltest.Entities.intermediate.fullDataWithIDs;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MappToWarehouseTest {

    @Inject
    private MappToWarehouse processor;

    private fullDataWithIDs row1 = new fullDataWithIDs(2017, 4, null, "Supplier1", null, "100", "item_desc1",
            "type1", null, 1.00, 0.00, 0.00);

    private fullDataWithIDs row2 = new fullDataWithIDs(2017, 2, null, "Supplier1", null, "102", "item_desc2",
            "type2", null, 1.00, 7.00, 0.00);

    private fullDataWithIDs row3 = new fullDataWithIDs(2017, 4, null, "Supplier2", null, "102", "item_desc2",
            "type2", null, 1.00, 8.00, 0.00);

    private fullDataWithIDs row4 = new fullDataWithIDs(2017, 4, null, "Supplier3", null, "103", "item_desc1",
            "type3", null, 1.00, 0.00, 7.00);

    @Test
    public void extractDateTestNew() throws Exception {

        List<dim_date> checkList = new ArrayList<dim_date>();

        processor.extractDate(row1, checkList);

        assertNotNull(checkList);
        assertEquals(1, checkList.get(0).getId());
        assertEquals((Integer) row1.getYear(), checkList.get(0).getYear());
        assertEquals((Integer) row1.getMonth(), checkList.get(0).getMonth());
        assertEquals(null, checkList.get(0).getDay());
    }

    @Test
    public void extractDateTestOld() throws Exception {

        List<dim_date> checkList = new ArrayList<>(Arrays.asList(new dim_date(1, row1.year, row1.month, null)));
        List<dim_date> copy = new ArrayList<>(checkList);

        processor.extractDate(row3, checkList);

        assertNotNull(checkList);

        assertTrue(checkList.size() == 1);
        assertEquals(copy.get(0).getId(), checkList.get(0).getId());
        assertEquals(copy.get(0).getYear(), checkList.get(0).getYear());
        assertEquals(copy.get(0).getMonth(), checkList.get(0).getMonth());
        assertEquals(copy.get(0).getDay(), checkList.get(0).getDay());
    }

    // @Test
    // public void extractSupplierTestNew() throws Exception {
    //
    // List<dim_date> checkList = new ArrayList<dim_date>();
    //
    // processor.extractDate(row1, checkList);
    //
    // assertNotNull(checkList);
    // assertEquals(1, checkList.get(0).getId());
    // // assertEquals((Integer) row1.YEAR, checkList.get(0).getYear());
    // // assertEquals((Integer) row1.MONTH, checkList.get(0).getMonth());
    // assertEquals(null, checkList.get(0).getDay());
    // }
    //
    // @Test
    // public void extractSupplierTestOld() throws Exception {
    //
    // List<dim_date> checkList = new ArrayList<>(Arrays.asList(new dim_date(1, row1.year, row1.month, null)));
    // List<dim_date> copy = new ArrayList<>(checkList);
    //
    // processor.extractDate(row1, checkList);
    //
    // assertNotNull(checkList);
    //
    // assertTrue(checkList.size() == 1);
    // assertEquals(copy.get(0).getId(), checkList.get(0).getId());
    // assertEquals(copy.get(0).getYear(), checkList.get(0).getYear());
    // assertEquals(copy.get(0).getMonth(), checkList.get(0).getMonth());
    // assertEquals(copy.get(0).getDay(), checkList.get(0).getDay());
    // }
}

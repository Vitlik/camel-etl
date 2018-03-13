package de.viadee.cameltest.Methods;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import de.viadee.cameltest.Entities.CsvData;
import de.viadee.cameltest.Entities.MapTarget;

public class MapperTest {

    @Test
    public void mapperTest() {

        @SuppressWarnings("rawtypes")
        Mapper mapper = new Mapper();

        CsvData sourceObject = new CsvData(2012, 4, "mySupplier", "myCode", "myDesc", "myType", 1.00, 2.00, 3.00);

        try {
            @SuppressWarnings("unchecked")
            MapTarget myMapTarget = (MapTarget) mapper.source(sourceObject)
                    .setTarget(MapTarget.class.getName())
                    .byName()
                    .left("itemType").right("itemTy")
                    .run();

            assertNull(myMapTarget.getItemCo());

            assertEquals(myMapTarget.getYear(), sourceObject.getYear());
            assertEquals(myMapTarget.getMonth(), sourceObject.getMonth());
            assertEquals(myMapTarget.getItemTy(), sourceObject.getItemType());
            assertEquals(myMapTarget.getItemDescription(), sourceObject.getItemDescription());
            assertEquals(myMapTarget.getSupplier(), sourceObject.getSupplier());

            assertEquals(myMapTarget.getRetailSales(), sourceObject.getRetailSales(), 0);
            assertEquals(myMapTarget.getRetailTransfers(), sourceObject.getRetailTransfers(), 0);
            assertEquals(myMapTarget.getWarehouseSales(), sourceObject.getWarehouseSales(), 0);

        } catch (InstantiationException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            throw new RuntimeException(e);
        }
    }
}

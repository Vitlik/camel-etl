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
                    .left("item_type").right("item_ty")
                    .run();

            assertNull(myMapTarget.getItem_co());

            assertEquals(myMapTarget.getYear(), sourceObject.getYear());
            assertEquals(myMapTarget.getMonth(), sourceObject.getMonth());
            assertEquals(myMapTarget.getItem_ty(), sourceObject.getItem_type());
            assertEquals(myMapTarget.getItem_description(), sourceObject.getItem_description());
            assertEquals(myMapTarget.getSupplier(), sourceObject.getSupplier());

            assertEquals(myMapTarget.getRetail_sales(), sourceObject.getRetail_sales(), 0);
            assertEquals(myMapTarget.getRetail_transfers(), sourceObject.getRetail_transfers(), 0);
            assertEquals(myMapTarget.getWarehouse_sales(), sourceObject.getWarehouse_sales(), 0);

        } catch (InstantiationException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            throw new RuntimeException(e);
        }
    }
}

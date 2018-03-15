package de.viadee.cameltest.Entities.Source;

import java.io.Serializable;

@SuppressWarnings("serial")
public class WarehouseAndRetailSalesId implements Serializable {

    private int year;

    private int month;

    private String supplier;

    private String itemCode;

    private String itemDescription;

    private String itemType;

    public WarehouseAndRetailSalesId() {

    }

    public WarehouseAndRetailSalesId(int year, int month, String supplier, String itemCode, String itemDescription,
            String itemType) {
        super();
        this.year = year;
        this.month = month;
        this.supplier = supplier;
        this.itemCode = itemCode;
        this.itemDescription = itemDescription;
        this.itemType = itemType;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public String getSupplier() {
        return supplier;
    }

    public String getItemCode() {
        return itemCode;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public String getItemType() {
        return itemType;
    }
}

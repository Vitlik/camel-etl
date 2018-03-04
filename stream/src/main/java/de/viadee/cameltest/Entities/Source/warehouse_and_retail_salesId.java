package de.viadee.cameltest.Entities.Source;

import java.io.Serializable;

public class warehouse_and_retail_salesId implements Serializable {

    private int year;

    private int month;

    private String supplier;

    private String item_code;

    private String item_description;

    private String item_type;

    public warehouse_and_retail_salesId() {

    }

    public warehouse_and_retail_salesId(int year, int month, String supplier, String item_code, String item_description,
            String item_type) {
        super();
        this.year = year;
        this.month = month;
        this.supplier = supplier;
        this.item_code = item_code;
        this.item_description = item_description;
        this.item_type = item_type;
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

    public String getItem_code() {
        return item_code;
    }

    public String getItem_description() {
        return item_description;
    }

    public String getItem_type() {
        return item_type;
    }

}

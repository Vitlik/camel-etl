package de.viadee.cameltest.Entities.Source;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@CsvRecord(separator = ",", skipFirstLine = true)
public class Warehouse_and_Retail_Sales {

    @DataField(pos = 1)
    private int year;

    @DataField(pos = 2)
    private int month;

    @DataField(pos = 3)
    private String supplier;

    @DataField(pos = 4)
    private String item_code;

    @DataField(pos = 5)
    private String item_description;

    @DataField(pos = 6)
    private String item_type;

    @DataField(pos = 7)
    private double retail_sales;

    @DataField(pos = 8)
    private double retail_transfers;

    @DataField(pos = 9)
    private double warehouse_sales;

    public Warehouse_and_Retail_Sales() {

    }

    public Warehouse_and_Retail_Sales(int year, int month, String supplier, String item_code, String item_description,
            String item_type, double retail_sales, double retail_transfers, double warehouse_sales) {
        this.year = year;
        this.month = month;
        this.supplier = supplier;
        this.item_code = item_code;
        this.item_description = item_description;
        this.item_type = item_type;
        this.retail_sales = retail_sales;
        this.retail_transfers = retail_transfers;
        this.warehouse_sales = warehouse_sales;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getItem_code() {
        return item_code;
    }

    public void setItem_code(String item_code) {
        this.item_code = item_code;
    }

    public String getItem_description() {
        return item_description;
    }

    public void setItem_description(String item_description) {
        this.item_description = item_description;
    }

    public String getItem_type() {
        return item_type;
    }

    public void setItem_type(String item_type) {
        this.item_type = item_type;
    }

    public double getRetail_sales() {
        return retail_sales;
    }

    public void setRetail_sales(double retail_sales) {
        this.retail_sales = retail_sales;
    }

    public double getRetail_transfers() {
        return retail_transfers;
    }

    public void setRetail_transfers(double retail_transfers) {
        this.retail_transfers = retail_transfers;
    }

    public double getWarehouse_sales() {
        return warehouse_sales;
    }

    public void setWarehouse_sales(double warehouse_sales) {
        this.warehouse_sales = warehouse_sales;
    }

}

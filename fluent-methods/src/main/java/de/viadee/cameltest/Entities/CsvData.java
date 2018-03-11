package de.viadee.cameltest.Entities;

public class CsvData {

    public int year;

    public int month;

    public String supplier;

    public String item_code;

    public String item_description;

    public String item_type;

    public double retail_sales;

    public double retail_transfers;

    public double warehouse_sales;

    public CsvData() {
    }

    public CsvData(int year, int month, String supplier, String item_code, String item_description, String item_type,
            double retail_sales, double retail_transfers, double warehouse_sales) {
        super();
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

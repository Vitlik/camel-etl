package de.viadee.cameltest.Entities;

public class MapTarget {

    public int year;

    public int month;

    public Integer date_id;

    public String supplier;

    public Integer supplier_id;

    public String item_co;

    public String item_description;

    public String item_ty;

    public Integer item_id;

    public double retail_sales;

    public double retail_transfers;

    public double warehouse_sales;

    public MapTarget() {
    }

    public MapTarget(int year, int month, Integer date_id, String supplier, Integer supplier_id, String item_code,
            String item_description, String item_type, Integer item_id, double retail_sales, double retail_transfers,
            double warehouse_sales) {
        this.year = year;
        this.month = month;
        this.date_id = date_id;
        this.supplier = supplier;
        this.supplier_id = supplier_id;
        this.item_co = item_code;
        this.item_description = item_description;
        this.item_ty = item_type;
        this.item_id = item_id;
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

    public Integer getDate_id() {
        return date_id;
    }

    public void setDate_id(Integer l) {
        this.date_id = l;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Integer getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(Integer supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getItem_co() {
        return item_co;
    }

    public void setItem_co(String item_co) {
        this.item_co = item_co;
    }

    public String getItem_description() {
        return item_description;
    }

    public void setItem_description(String item_description) {
        this.item_description = item_description;
    }

    public String getItem_ty() {
        return item_ty;
    }

    public void setItem_ty(String item_ty) {
        this.item_ty = item_ty;
    }

    public Integer getItem_id() {
        return item_id;
    }

    public void setItem_id(Integer item_id) {
        this.item_id = item_id;
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

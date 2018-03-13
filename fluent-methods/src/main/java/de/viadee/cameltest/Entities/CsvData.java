package de.viadee.cameltest.Entities;

public class CsvData {

    public int year;

    public int month;

    public String supplier;

    public String itemCode;

    public String itemDescription;

    public String itemType;

    public double retailSales;

    public double retailTransfers;

    public double warehouseSales;

    public CsvData() {
    }

    public CsvData(int year, int month, String supplier, String itemCode, String itemDescription, String itemType,
            double retailSales, double retailTransfers, double warehouseSales) {
        super();
        this.year = year;
        this.month = month;
        this.supplier = supplier;
        this.itemCode = itemCode;
        this.itemDescription = itemDescription;
        this.itemType = itemType;
        this.retailSales = retailSales;
        this.retailTransfers = retailTransfers;
        this.warehouseSales = warehouseSales;
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

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public double getRetailSales() {
        return retailSales;
    }

    public void setRetailSales(double retailSales) {
        this.retailSales = retailSales;
    }

    public double getRetailTransfers() {
        return retailTransfers;
    }

    public void setRetailTransfers(double retailTransfers) {
        this.retailTransfers = retailTransfers;
    }

    public double getWarehouseSales() {
        return warehouseSales;
    }

    public void setWarehouseSales(double warehouseSales) {
        this.warehouseSales = warehouseSales;
    }

}

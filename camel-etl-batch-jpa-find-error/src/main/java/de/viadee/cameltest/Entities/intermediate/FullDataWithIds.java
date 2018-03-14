package de.viadee.cameltest.Entities.intermediate;

public class FullDataWithIds {

    public int year;

    public int month;

    public long dateId;

    public String supplier;

    public long supplierId;

    public String itemCode;

    public String itemDescription;

    public String itemType;

    public long itemId;

    public double retailSales;

    public double retailTransfers;

    public double warehouseSales;

    public FullDataWithIds() {
    }

    public FullDataWithIds(int year, int month, long dateId, String supplier, long supplierId, String itemCode,
            String itemDescription, String itemType, long itemId, double retailSales, double retailTransfers,
            double warehouseSales) {
        super();
        this.year = year;
        this.month = month;
        this.dateId = dateId;
        this.supplier = supplier;
        this.supplierId = supplierId;
        this.itemCode = itemCode;
        this.itemDescription = itemDescription;
        this.itemType = itemType;
        this.itemId = itemId;
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

    public long getDateId() {
        return dateId;
    }

    public void setDateId(long dateId) {
        this.dateId = dateId;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(long supplierId) {
        this.supplierId = supplierId;
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

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
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

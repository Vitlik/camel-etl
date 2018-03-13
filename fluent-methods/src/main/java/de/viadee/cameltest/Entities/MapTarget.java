package de.viadee.cameltest.Entities;

public class MapTarget {

    public int year;

    public int month;

    public Integer dateId;

    public String supplier;

    public Integer supplierId;

    public String itemCo;

    public String itemDescription;

    public String itemTy;

    public Integer itemId;

    public double retailSales;

    public double retailTransfers;

    public double warehouseSales;

    public MapTarget() {
    }

    public MapTarget(int year, int month, Integer dateId, String supplier, Integer supplierId, String itemCo,
            String itemDescription, String itemTy, Integer itemId, double retailSales, double retailTransfers,
            double warehouseSales) {
        this.year = year;
        this.month = month;
        this.dateId = dateId;
        this.supplier = supplier;
        this.supplierId = supplierId;
        this.itemCo = itemCo;
        this.itemDescription = itemDescription;
        this.itemTy = itemTy;
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

    public Integer getDateId() {
        return dateId;
    }

    public void setDateId(Integer dateId) {
        this.dateId = dateId;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getItemCo() {
        return itemCo;
    }

    public void setItemCo(String itemCo) {
        this.itemCo = itemCo;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemTy() {
        return itemTy;
    }

    public void setItemTy(String itemTy) {
        this.itemTy = itemTy;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
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

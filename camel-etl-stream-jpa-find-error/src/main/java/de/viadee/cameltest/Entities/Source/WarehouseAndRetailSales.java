package de.viadee.cameltest.Entities.Source;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(WarehouseAndRetailSalesId.class)
@Table(name = "warehouse_and_retail_sales", schema = "mydb")
public class WarehouseAndRetailSales {

    @Id
    private int year;

    @Id
    private int month;

    @Id
    private String supplier;

    @Id
    @Column(name = "item_code")
    private String itemCode;

    @Id
    @Column(name = "item_description")
    private String itemDescription;

    @Id
    @Column(name = "item_type")
    private String itemType;

    @Column(name = "retail_sales")
    private double retailSales;

    @Column(name = "retail_transfers")
    private double retailTransfers;

    @Column(name = "warehouse_sales")
    private double warehouseSales;

    public WarehouseAndRetailSales() {

    }

    public WarehouseAndRetailSales(int year, int month, String supplier, String itemCode, String itemDescription,
            String itemType, double retailSales, double retailTransfers, double warehouseSales) {
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

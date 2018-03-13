package de.viadee.cameltest.Entities;

public class AggregatedData {

    public int year;

    public int month;

    public String supplier;

    public String itemType;

    public double retailSalesSum;

    public double retailTransfersAvg;

    public double warehouseSalesMax;

    public AggregatedData() {
    }

    public AggregatedData(int year, int month, String supplier, String itemType, double retailSalesSum,
            double retailTransfersAvg, double warehouseSalesMax) {
        this.year = year;
        this.month = month;
        this.supplier = supplier;
        this.itemType = itemType;
        this.retailSalesSum = retailSalesSum;
        this.retailTransfersAvg = retailTransfersAvg;
        this.warehouseSalesMax = warehouseSalesMax;
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

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public double getRetailSalesSum() {
        return retailSalesSum;
    }

    public void setRetailSalesSum(double retailSalesSum) {
        this.retailSalesSum = retailSalesSum;
    }

    public double getRetailTransfersAvg() {
        return retailTransfersAvg;
    }

    public void setRetailTransfersAvg(double retailTransfersAvg) {
        this.retailTransfersAvg = retailTransfersAvg;
    }

    public double getWarehouseSalesMax() {
        return warehouseSalesMax;
    }

    public void setWarehouseSalesMax(double warehouseSalesMax) {
        this.warehouseSalesMax = warehouseSalesMax;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((itemType == null) ? 0 : itemType.hashCode());
        result = prime * result + month;
        long temp;
        temp = Double.doubleToLongBits(retailSalesSum);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(retailTransfersAvg);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((supplier == null) ? 0 : supplier.hashCode());
        temp = Double.doubleToLongBits(warehouseSalesMax);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + year;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AggregatedData other = (AggregatedData) obj;
        if (itemType == null) {
            if (other.itemType != null)
                return false;
        } else if (!itemType.equals(other.itemType))
            return false;
        if (month != other.month)
            return false;
        if (Double.doubleToLongBits(retailSalesSum) != Double.doubleToLongBits(other.retailSalesSum))
            return false;
        if (Double.doubleToLongBits(retailTransfersAvg) != Double.doubleToLongBits(other.retailTransfersAvg))
            return false;
        if (supplier == null) {
            if (other.supplier != null)
                return false;
        } else if (!supplier.equals(other.supplier))
            return false;
        if (Double.doubleToLongBits(warehouseSalesMax) != Double.doubleToLongBits(other.warehouseSalesMax))
            return false;
        if (year != other.year)
            return false;
        return true;
    }

}

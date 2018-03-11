package de.viadee.cameltest.Entities;

public class AggregatedData {

    public int year;

    public int month;

    public String supplier;

    public String item_type;

    public double retail_sales_sum;

    public double retail_transfers_avg;

    public double warehouse_sales_max;

    public AggregatedData() {
    }

    public AggregatedData(int year, int month, String supplier, String item_type, double retail_sales_sum,
            double retail_transfers_avg, double warehouse_sales_max) {
        super();
        this.year = year;
        this.month = month;
        this.supplier = supplier;
        this.item_type = item_type;
        this.retail_sales_sum = retail_sales_sum;
        this.retail_transfers_avg = retail_transfers_avg;
        this.warehouse_sales_max = warehouse_sales_max;
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

    public String getItem_type() {
        return item_type;
    }

    public void setItem_type(String item_type) {
        this.item_type = item_type;
    }

    public double getRetail_sales_sum() {
        return retail_sales_sum;
    }

    public void setRetail_sales_sum(double retail_sales_sum) {
        this.retail_sales_sum = retail_sales_sum;
    }

    public double getRetail_transfers_avg() {
        return retail_transfers_avg;
    }

    public void setRetail_transfers_avg(double retail_transfers_avg) {
        this.retail_transfers_avg = retail_transfers_avg;
    }

    public double getWarehouse_sales_max() {
        return warehouse_sales_max;
    }

    public void setWarehouse_sales_max(double warehouse_sales_max) {
        this.warehouse_sales_max = warehouse_sales_max;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((item_type == null) ? 0 : item_type.hashCode());
        result = prime * result + month;
        long temp;
        temp = Double.doubleToLongBits(retail_sales_sum);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(retail_transfers_avg);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((supplier == null) ? 0 : supplier.hashCode());
        temp = Double.doubleToLongBits(warehouse_sales_max);
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
        if (item_type == null) {
            if (other.item_type != null)
                return false;
        } else if (!item_type.equals(other.item_type))
            return false;
        if (month != other.month)
            return false;
        if (Double.doubleToLongBits(retail_sales_sum) != Double.doubleToLongBits(other.retail_sales_sum))
            return false;
        if (Double.doubleToLongBits(retail_transfers_avg) != Double.doubleToLongBits(other.retail_transfers_avg))
            return false;
        if (supplier == null) {
            if (other.supplier != null)
                return false;
        } else if (!supplier.equals(other.supplier))
            return false;
        if (Double.doubleToLongBits(warehouse_sales_max) != Double.doubleToLongBits(other.warehouse_sales_max))
            return false;
        if (year != other.year)
            return false;
        return true;
    }

}

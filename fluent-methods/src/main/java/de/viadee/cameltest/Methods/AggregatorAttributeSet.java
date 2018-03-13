package de.viadee.cameltest.Methods;

public interface AggregatorAttributeSet {

    /**
     * Set the Operation for the aggregation.
     * 
     * @param countMaxMinSumOther
     *            Choose one of the following strings "max","min","sum","avg","first","last","count"
     * @return Returns an interface of OperationSet so the target attribute can be set.
     */
    AggregatorOperationSet setOperation(String countMaxMinSumOther);
}

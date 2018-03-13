package de.viadee.cameltest.Methods;

public interface AggregatorAttributeTargetSet {

    /**
     * Set the source attribute for the aggregation.
     * 
     * @param attributeName
     *            Set the name of an object attribute of the source data.
     * @return Returns an interface of AggregatorAttributeSet so the operation can be set.
     */
    AggregatorAttributeSet setAttribute(String attributeName);

    /**
     * The aggregation is ready to be executed.
     */
    Object run();
}

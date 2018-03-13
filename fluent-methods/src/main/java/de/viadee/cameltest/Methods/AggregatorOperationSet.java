package de.viadee.cameltest.Methods;

public interface AggregatorOperationSet {

    /**
     * Set the target attribute name for the aggregation.
     * 
     * @param attributeTargetName
     *            Set the name of an object attribute of the target class.
     * @return Returns an interface of AggregatorAttributeTargetSet so the aggregation can be run or further operations
     *         can be defined.
     */
    AggregatorAttributeTargetSet setAttributeTarget(String attributeTargetName);
}

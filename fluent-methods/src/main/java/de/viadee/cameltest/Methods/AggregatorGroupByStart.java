package de.viadee.cameltest.Methods;

public interface AggregatorGroupByStart {

    /**
     * Set the attribute name / groupBy column for the aggregation.
     * 
     * @param attributeName
     *            Name of an object attribute of the source data.
     * @return Returns an interface of AggregatorGroupByStarted so the aggregation can be run or some operations can be
     *         set.
     */
    AggregatorGroupByStarted groupBy(String attributeName);
}

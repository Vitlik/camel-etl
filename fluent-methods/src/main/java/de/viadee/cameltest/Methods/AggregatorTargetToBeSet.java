package de.viadee.cameltest.Methods;

public interface AggregatorTargetToBeSet {

    /**
     * Set target object class name.
     * 
     * @param target
     *            Name of the target class. Best filled by using TargetClass.class.getName().
     * @return Returns an interface of AggregatorGroupByStarted so the aggregation can be run or some operations can be
     *         set.
     */
    AggregatorGroupByStart setTarget(String target);
}

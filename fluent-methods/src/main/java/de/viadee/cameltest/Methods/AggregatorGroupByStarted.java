package de.viadee.cameltest.Methods;

public interface AggregatorGroupByStarted {

    AggregatorGroupByStarted groupBy(String attributeName);

    AggregatorAttributeSet setAttribute(String attributeName);

    Object run();
}

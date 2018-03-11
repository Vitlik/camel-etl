package de.viadee.cameltest.Methods;

public interface MapperExecutionAllowed {

    MapperLeftWasSet left(String left);

    Object run() throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException;
}

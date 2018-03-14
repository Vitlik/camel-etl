package de.viadee.cameltest.Processes;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.DefaultExchange;
import org.junit.Test;

import de.viadee.cameltest.Entities.intermediate.FullDataWithIds;

public class CleanDataTest {

    @Test
    public void cleanDataTest() throws Exception {

        CamelContext context = new DefaultCamelContext();
        Exchange ex = new DefaultExchange(context);
        CleanData cleanData = new CleanData();
        FullDataWithIds testData = new FullDataWithIds();
        List<FullDataWithIds> testList = new ArrayList<FullDataWithIds>();

        testData.setItemDescription("  Der äußerliche Schein könnte trügen.     ");
        testList.add(testData);
        ex.getIn().setBody(testList);
        cleanData.process(ex);
        @SuppressWarnings("unchecked")
        List<FullDataWithIds> newList = (List<FullDataWithIds>) ex.getOut().getBody();

        assertEquals(newList.get(0).getItemDescription(), "Der aeusserliche Schein koennte truegen.");
    }

    @Test
    public void testTrim() throws Exception {
        CleanData cleanData = new CleanData();
        FullDataWithIds testData = new FullDataWithIds();

        testData.setItemDescription(" Der äußerliche Schein könnte trügen. ");
        cleanData.trimAndExchange(testData);

        assertEquals(testData.getItemDescription(), "Der aeusserliche Schein koennte truegen.");
    }

}

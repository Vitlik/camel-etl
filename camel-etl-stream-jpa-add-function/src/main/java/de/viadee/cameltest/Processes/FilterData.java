package de.viadee.cameltest.Processes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import de.viadee.cameltest.Entities.intermediate.FullDataWithIds;

@Component
public class FilterData implements Processor {

    @Override
    public void process(Exchange exchange) {

        FullDataWithIds row = exchange.getIn().getBody(FullDataWithIds.class);

        trimAndExchange(row);
        exchange.getOut().setBody(row);
    }

    public void trimAndExchange(FullDataWithIds data) {
        String toClean = data.getItemDescription();
        int endIdx = toClean.lastIndexOf("-");

        if (endIdx != -1) {
            toClean = toClean.substring(0, endIdx);
        }

        data.setItemDescription(toClean);
    }
}

package de.viadee.cameltest.Processes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import de.viadee.cameltest.Entities.intermediate.FullDataWithIds;

@Component
public class CleanData implements Processor {

    @Override
    public void process(Exchange exchange) {

        FullDataWithIds row = exchange.getIn().getBody(FullDataWithIds.class);

        trimAndExchange(row);
        exchange.getOut().setBody(row);
    }

    public void trimAndExchange(FullDataWithIds data) {
        String toClean = data.getItemDescription();
        toClean = toClean.trim();
        toClean = toClean.replaceAll("ß", "ss");
        toClean = toClean.replaceAll("ä", "ae");
        toClean = toClean.replaceAll("ö", "oe");
        toClean = toClean.replaceAll("ü", "ue");
        data.setItemDescription(toClean);
    }
}

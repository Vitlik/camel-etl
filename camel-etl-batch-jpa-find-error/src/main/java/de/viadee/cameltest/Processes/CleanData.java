package de.viadee.cameltest.Processes;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import de.viadee.cameltest.Entities.intermediate.FullDataWithIds;

@Component
public class CleanData implements Processor {

    @Override
    public void process(Exchange exchange) {

        @SuppressWarnings("unchecked")
        List<FullDataWithIds> dataList = (List<FullDataWithIds>) exchange.getIn().getBody();

        for (FullDataWithIds row : dataList) {
            trimAndExchange(row);
        }
        exchange.getOut().setBody(dataList);
    }

    public void trimAndExchange(FullDataWithIds data) {
        String toClean = data.getItemDescription();
        toClean = toClean.trim();
        toClean = toClean.replaceAll("ß", "ss");
        toClean = toClean.replaceAll("ä", "ae");
        toClean = toClean.replaceAll("ö", "oe");
        toClean = toClean.replaceAll("ö", "ue");
        data.setItemDescription(toClean);
    }
}

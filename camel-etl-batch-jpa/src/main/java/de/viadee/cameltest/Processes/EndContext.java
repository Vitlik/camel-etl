package de.viadee.cameltest.Processes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Component;

@Component
public class EndContext implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

        ProducerTemplate template = exchange.getContext().createProducerTemplate();

        template.sendBody("controlbus:language:simple?async=true", "${camelContext.stop()}");
    }

}

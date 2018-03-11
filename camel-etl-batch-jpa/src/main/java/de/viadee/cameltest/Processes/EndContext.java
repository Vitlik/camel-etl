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

        // stop this route using a thread that will stop
        // this route gracefully while we are still running
        // if (stop == null) {
        // stop = new Thread() {
        //
        // @Override
        // public void run() {
        // try {
        // context.stopRoute("minimal-camel-route");
        // } catch (Exception e) {
        // // ignore
        // } finally {
        // // signal we stopped the route
        // latch.countDown();
        // }
        // }
        // };
        // }
        //
        // // start the thread that stops this route
        // stop.start();
        //
        // context.stop();
    }

}

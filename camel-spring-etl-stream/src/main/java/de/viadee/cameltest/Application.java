/**
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements. See the NOTICE
 * file distributed with this work for additional information regarding copyright ownership. The ASF licenses this file
 * to You under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package de.viadee.cameltest;

import javax.inject.Inject;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.stereotype.Component;

import de.viadee.cameltest.Processes.DateDimProcess;
import de.viadee.cameltest.Processes.ItemDimProcess;
import de.viadee.cameltest.Processes.MappToWarehouse;
import de.viadee.cameltest.Processes.SupplierDimProcess;
import de.viadee.cameltest.Processes.WriteFacts;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Component
    public class MyRoute extends RouteBuilder {

        @Inject
        private MappToWarehouse mapToWarehouse;

        @Inject
        private DateDimProcess dateDimProcess;

        @Inject
        private ItemDimProcess itemDimProcess;

        @Inject
        private SupplierDimProcess supplierDimProcess;

        @Inject
        private WriteFacts writeFacts;

        @Override
        public void configure() throws Exception {

            from("jpa:de.viadee.cameltest.Entities.Source.warehouse_and_retail_sales"
                    + "?consumeLockEntity=false"
                    // + "&consumer.delay=100"
                    + "&maxMessagesPerPoll=100"
                    + "&consumeDelete=false")
                            .routeId("sales-dim-mapping")
                            .process(mapToWarehouse)
                            .process(dateDimProcess)
                            .process(itemDimProcess)
                            .process(supplierDimProcess)
                            .process(writeFacts)

                            .log("Processed ${body.year} ${body.month} ${body.supplier} ${body.item_code} ${body.item_type} ${body.item_description} ");
        }
    }
}
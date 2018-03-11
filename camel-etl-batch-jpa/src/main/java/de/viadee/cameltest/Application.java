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
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.stereotype.Component;

import de.viadee.cameltest.Processes.CleanData;
import de.viadee.cameltest.Processes.DateDimProcessList;
import de.viadee.cameltest.Processes.EndContext;
import de.viadee.cameltest.Processes.GetData;
import de.viadee.cameltest.Processes.ItemDimProcessList;
import de.viadee.cameltest.Processes.MappToWarehouseList;
import de.viadee.cameltest.Processes.SupplierDimProcessList;
import de.viadee.cameltest.Processes.WriteFactsList;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Component
    public class MyRoute extends RouteBuilder {

        @Inject
        private EndContext endContext;

        @Inject
        private GetData getData;

        @Inject
        private MappToWarehouseList mapToWarehouseList;

        @Inject
        private CleanData cleanData;

        @Inject
        private DateDimProcessList dateDimProcessList;

        @Inject
        private ItemDimProcessList itemDimProcessList;

        @Inject
        private SupplierDimProcessList supplierDimProcessList;

        @Inject
        private WriteFactsList writeFactsList;

        @Override
        public void configure() throws Exception {

            from("timer://runOnce?delay=-1&repeatCount=1")
                    .routeId("minimal-camel-route") // if changed, also change in EndContext
                    .log("Started route.")

                    .process(getData)
                    .process(mapToWarehouseList)
                    .process(cleanData)

                    .process(dateDimProcessList)
                    .process(itemDimProcessList)
                    .process(supplierDimProcessList)
                    .process(writeFactsList)

                    .log("Finished route.")
                    .process(endContext);
        }
    }
}
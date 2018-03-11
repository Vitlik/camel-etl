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

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // @Component
    // public class MyRoute extends RouteBuilder {
    //
    // @Inject
    // private MappToWarehouse mappToWarehouse;
    //
    // @Inject
    // private DateProcess dateProcess;
    //
    // @Inject
    // private ItemProcess itemProcess;
    //
    // @Inject
    // private SupplierProcess supplierProcess;
    //
    // @Inject
    // private WriteFacts writeFacts;
    //
    // @Override
    // public void configure() throws Exception {
    //
    // from("jpa:de.viadee.cameltest.Entities.Source.warehouse_and_retail_sales"
    // + "?consumeLockEntity=false"
    // // + "&consumer.delay=100"
    // + "&maxMessagesPerPoll=100"
    // + "&consumeDelete=false")
    // .routeId("sales-dim-mapping")
    // .process(mappToWarehouse)
    // .process(dateProcess)
    // .process(itemProcess)
    // .process(supplierProcess)
    // .process(writeFacts)
    //
    // // Aggregation may improve performance when used. copied examnple code below
    // // .aggregate(header("PROD_TYPE"), new SQLStrategy()).completionSize(100)
    // // .completionTimeout(1000)
    // // .to("sql:insert into products (price, description) values (#, #)?batch=true")
    //
    // .log("Processed ${body.year} ${body.month} ${body.supplier} ${body.item_code} ${body.item_type}
    // ${body.item_description} ");
    // }
    // }
}
package de.viadee.cameltest;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import de.viadee.cameltest.Entities.Target.dim_date;
import de.viadee.cameltest.Entities.Target.dim_item;
import de.viadee.cameltest.Entities.Target.dim_supplier;
import de.viadee.cameltest.Entities.Target.fact_sales;
import de.viadee.cameltest.Entities.Target.Repos.dim_dateRepository;
import de.viadee.cameltest.Entities.Target.Repos.dim_itemRepository;
import de.viadee.cameltest.Entities.Target.Repos.dim_supplierRepository;
import de.viadee.cameltest.Entities.Target.Repos.fact_salesRepository;

@SpringBootApplication
public class Application {

    @Bean
    public Loader<dim_date> DateRefLoader(dim_dateRepository refRepo) {
        return new Loader<dim_date>(refRepo);
    }

    @Bean
    public Loader<dim_item> itemRefLoader(dim_itemRepository refRepo) {
        return new Loader<dim_item>(refRepo);
    }

    @Bean
    public Loader<dim_supplier> supplierRrefLoader(dim_supplierRepository refRepo) {
        return new Loader<dim_supplier>(refRepo);
    }

    @Bean
    public Loader<fact_sales> salesRefLoader(fact_salesRepository refRepo) {
        return new Loader<fact_sales>(refRepo);
    }

    @Bean
    public CamelContext context(MyRoute myRoute) throws Exception {
        CamelContext context = new DefaultCamelContext();
        context.addRoutes(myRoute);
        context.start();
        Thread.sleep(20000);
        return context;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

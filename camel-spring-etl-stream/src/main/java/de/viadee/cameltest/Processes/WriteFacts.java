package de.viadee.cameltest.Processes;

import javax.inject.Inject;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import de.viadee.cameltest.Entities.Target.fact_sales;
import de.viadee.cameltest.Entities.Target.Repos.fact_salesRepository;
import de.viadee.cameltest.Entities.intermediate.fullDataWithIDs;

@Component
public class WriteFacts implements Processor {

    private static final Logger LOGGER = Logger.getLogger(WriteFacts.class);

    @Inject
    private fact_salesRepository factRepo;

    @Inject
    JdbcTemplate jdbcTemplate;

    @Override
    public void process(Exchange exchange) {

        fullDataWithIDs saleWithIds = exchange.getIn().getBody(fullDataWithIDs.class);

        // if fact already exists, skip
        if (jdbcTemplate.queryForObject(
                "SELECT date_id FROM fact_sales WHERE date_id = ? AND supplier_id = ? AND item_id = ?",
                new Object[] { saleWithIds.getDate_id(), saleWithIds.getSupplier_id(), saleWithIds.getItem_id() },
                Integer.class) != null) {
            LOGGER.debug("Fact already existing. ");
            return;
        }

        fact_sales factData = new fact_sales();

        BeanUtils.copyProperties(saleWithIds, factData);

        factRepo.saveAndFlush(factData);
    }
}

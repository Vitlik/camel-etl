package de.viadee.cameltest.Processes;

import javax.inject.Inject;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import de.viadee.cameltest.Entities.Target.DimDate;
import de.viadee.cameltest.Entities.Target.Repos.DimDateRepository;
import de.viadee.cameltest.Entities.intermediate.FullDataWithIds;

@Component
public class DateDimProcess implements Processor {

    private static final Logger LOGGER = Logger.getLogger(DateDimProcess.class);

    @Inject
    private DimDateRepository dateRepo;

    @Inject
    JdbcTemplate jdbcTemplate;

    @Override
    public void process(Exchange exchange) {

        FullDataWithIds row = exchange.getIn().getBody(FullDataWithIds.class);

        DimDate dimDate = dateRepo.findByYearAndMonth(row.getYear(), row.getMonth());

        if (dimDate == null) {
            dimDate = new DimDate(row.getYear(), row.getMonth(), null);
            dateRepo.saveAndFlush(dimDate);
        }
        row.setDateId(dimDate.getId());

        exchange.getOut().setBody(row);
    }
}

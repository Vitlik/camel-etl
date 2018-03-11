package de.viadee.cameltest.Processes;

import javax.inject.Inject;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import de.viadee.cameltest.Entities.Target.dim_date;
import de.viadee.cameltest.Entities.Target.Repos.dim_dateRepository;
import de.viadee.cameltest.Entities.intermediate.fullDataWithIDs;

@Component
public class DateDimProcess implements Processor {

    private static final Logger LOGGER = Logger.getLogger(DateDimProcess.class);

    @Inject
    private dim_dateRepository dateRepo;

    @Inject
    JdbcTemplate jdbcTemplate;

    @Override
    public void process(Exchange exchange) {

        fullDataWithIDs row = exchange.getIn().getBody(fullDataWithIDs.class);

        dim_date dimDate = dateRepo.findByYearAndMonth(row.getYear(), row.getMonth());

        if (dimDate != null) {
            row.setDate_id(dimDate.getId());
        } else {
            row.setDate_id(createDateEntry(row));
        }

        exchange.getOut().setBody(row);
    }

    private Integer createDateEntry(fullDataWithIDs row) {
        Integer lastId = jdbcTemplate.queryForObject("Select max(id) from dim_date", Integer.class);

        if (lastId == null) {
            lastId = 0;
        }

        dim_date dimDate = new dim_date(lastId + 1, row.getYear(), row.getMonth(), null);

        dateRepo.saveAndFlush(dimDate);

        return lastId + 1;
    }
}

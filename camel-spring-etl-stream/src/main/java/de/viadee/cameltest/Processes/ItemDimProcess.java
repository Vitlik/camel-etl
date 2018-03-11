package de.viadee.cameltest.Processes;

import javax.inject.Inject;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import de.viadee.cameltest.Entities.Target.dim_item;
import de.viadee.cameltest.Entities.Target.Repos.dim_itemRepository;
import de.viadee.cameltest.Entities.intermediate.fullDataWithIDs;

@Component
public class ItemDimProcess implements Processor {

    private static final Logger LOGGER = Logger.getLogger(ItemDimProcess.class);

    @Inject
    private dim_itemRepository itemRepo;

    @Inject
    JdbcTemplate jdbcTemplate;

    @Override
    public void process(Exchange exchange) {

        fullDataWithIDs row = exchange.getIn().getBody(fullDataWithIDs.class);

        dim_item dimItem = itemRepo.findByTypeAndCodeAndDescription(row.getItem_type(), row.getItem_code(),
                row.getItem_description());

        if (dimItem != null) {
            row.setItem_id(dimItem.getId());
        } else {
            row.setItem_id(createItemEntry(row));
        }

        exchange.getOut().setBody(row);
    }

    private Integer createItemEntry(fullDataWithIDs row) {
        Integer lastId = jdbcTemplate.queryForObject("Select max(id) from dim_item", Integer.class);

        if (lastId == null) {
            lastId = 0;
        }

        dim_item dimItem = new dim_item(lastId + 1, row.getItem_type(), row.getItem_code(), row.getItem_description());

        itemRepo.saveAndFlush(dimItem);

        return lastId + 1;
    }
}

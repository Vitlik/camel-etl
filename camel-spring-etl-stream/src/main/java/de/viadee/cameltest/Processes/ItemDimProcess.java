package de.viadee.cameltest.Processes;

import javax.inject.Inject;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import de.viadee.cameltest.Entities.Target.DimItem;
import de.viadee.cameltest.Entities.Target.Repos.DimItemRepository;
import de.viadee.cameltest.Entities.intermediate.FullDataWithIds;

@Component
public class ItemDimProcess implements Processor {

    private static final Logger LOGGER = Logger.getLogger(ItemDimProcess.class);

    @Inject
    private DimItemRepository itemRepo;

    @Inject
    JdbcTemplate jdbcTemplate;

    @Override
    public void process(Exchange exchange) {

        FullDataWithIds row = exchange.getIn().getBody(FullDataWithIds.class);

        DimItem dimItem = itemRepo.findByTypeAndCodeAndDescription(row.getItemType(), row.getItemCode(),
                row.getItemDescription());

        if (dimItem != null) {
            row.setItemId(dimItem.getId());
        } else {
            dimItem = new DimItem(row.getItemType(), row.getItemCode(), row.getItemDescription());
            itemRepo.saveAndFlush(dimItem);
            row.setItemId(dimItem.getId());
        }

        exchange.getOut().setBody(row);
    }
}

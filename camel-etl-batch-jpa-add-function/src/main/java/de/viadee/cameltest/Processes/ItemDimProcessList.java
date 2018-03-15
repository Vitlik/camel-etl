package de.viadee.cameltest.Processes;

import java.util.List;

import javax.inject.Inject;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import de.viadee.cameltest.Entities.Target.DimItem;
import de.viadee.cameltest.Entities.Target.Repos.DimItemRepository;
import de.viadee.cameltest.Entities.intermediate.FullDataWithIds;

@Component
public class ItemDimProcessList implements Processor {

    @Inject
    private DimItemRepository itemRepo;

    @Override
    public void process(Exchange exchange) {

        @SuppressWarnings("unchecked")
        List<FullDataWithIds> dataList = (List<FullDataWithIds>) exchange.getIn().getBody();

        for (FullDataWithIds row : dataList) {

            DimItem dimItem = itemRepo.findByTypeAndCodeAndDescription(row.getItemType(), row.getItemCode(),
                    row.getItemDescription());

            if (dimItem == null) {
                dimItem = new DimItem(row.getItemType(), row.getItemCode(), row.getItemDescription());
                itemRepo.saveAndFlush(dimItem);
            }
            row.setItemId(dimItem.getId());
        }

        exchange.getOut().setBody(dataList);
    }
}

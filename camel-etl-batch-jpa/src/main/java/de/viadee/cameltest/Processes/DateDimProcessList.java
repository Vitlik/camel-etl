package de.viadee.cameltest.Processes;

import java.util.List;

import javax.inject.Inject;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import de.viadee.cameltest.Entities.Target.DimDate;
import de.viadee.cameltest.Entities.Target.Repos.DimDateRepository;
import de.viadee.cameltest.Entities.intermediate.FullDataWithIds;

@Component
public class DateDimProcessList implements Processor {

    @Inject
    private DimDateRepository dateRepo;

    @Override
    public void process(Exchange exchange) {

        @SuppressWarnings("unchecked")
        List<FullDataWithIds> dataList = (List<FullDataWithIds>) exchange.getIn().getBody();

        for (FullDataWithIds row : dataList) {

            DimDate dimDate = dateRepo.findByYearAndMonth(row.getYear(), row.getMonth());

            if (dimDate != null) {
                row.setDateId(dimDate.getId());
            } else {
                dimDate = new DimDate(row.getYear(), row.getMonth(), null);
                dateRepo.saveAndFlush(dimDate);
                row.setDateId(dimDate.getId());
            }
        }

        exchange.getOut().setBody(dataList);
    }
}

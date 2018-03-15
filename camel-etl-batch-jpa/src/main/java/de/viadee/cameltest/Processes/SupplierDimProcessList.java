package de.viadee.cameltest.Processes;

import java.util.List;

import javax.inject.Inject;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import de.viadee.cameltest.Entities.Target.DimSupplier;
import de.viadee.cameltest.Entities.Target.Repos.DimSupplierRepository;
import de.viadee.cameltest.Entities.intermediate.FullDataWithIds;

@Component
public class SupplierDimProcessList implements Processor {

    @Inject
    private DimSupplierRepository supplierRepo;

    @Inject
    JdbcTemplate jdbcTemplate;

    @Override
    public void process(Exchange exchange) {

        @SuppressWarnings("unchecked")
        List<FullDataWithIds> dataList = (List<FullDataWithIds>) exchange.getIn().getBody();

        for (FullDataWithIds row : dataList) {

            DimSupplier dimSupplier = supplierRepo.findByName(row.getSupplier());

            if (dimSupplier == null) {
                dimSupplier = new DimSupplier(row.getSupplier());
                supplierRepo.saveAndFlush(dimSupplier);
            }
            row.setSupplierId(dimSupplier.getId());
        }

        exchange.getOut().setBody(dataList);
    }
}

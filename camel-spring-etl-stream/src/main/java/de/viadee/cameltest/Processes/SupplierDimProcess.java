package de.viadee.cameltest.Processes;

import javax.inject.Inject;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import de.viadee.cameltest.Entities.Target.DimSupplier;
import de.viadee.cameltest.Entities.Target.Repos.DimSupplierRepository;
import de.viadee.cameltest.Entities.intermediate.FullDataWithIds;

@Component
public class SupplierDimProcess implements Processor {

    private static final Logger LOGGER = Logger.getLogger(SupplierDimProcess.class);

    @Inject
    private DimSupplierRepository supplierRepo;

    @Inject
    JdbcTemplate jdbcTemplate;

    @Override
    public void process(Exchange exchange) {

        FullDataWithIds row = exchange.getIn().getBody(FullDataWithIds.class);

        DimSupplier dimSupplier = supplierRepo.findByName(row.getSupplier());

        if (dimSupplier != null) {
            row.setSupplierId(dimSupplier.getId());
        } else {
            dimSupplier = new DimSupplier(row.getSupplier());
            supplierRepo.saveAndFlush(dimSupplier);
            row.setSupplierId(dimSupplier.getId());
        }

        exchange.getOut().setBody(row);
    }
}

package de.viadee.cameltest.Processes;

import javax.inject.Inject;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import de.viadee.cameltest.Entities.Target.dim_supplier;
import de.viadee.cameltest.Entities.Target.Repos.dim_supplierRepository;
import de.viadee.cameltest.Entities.intermediate.fullDataWithIDs;

@Component
public class SupplierProcess implements Processor {

    private static final Logger LOGGER = Logger.getLogger(SupplierProcess.class);

    @Inject
    private dim_supplierRepository supplierRepo;

    @Inject
    JdbcTemplate jdbcTemplate;

    @Override
    public void process(Exchange exchange) {

        fullDataWithIDs row = exchange.getIn().getBody(fullDataWithIDs.class);

        dim_supplier dimSupplier = supplierRepo.findByName(row.getSupplier());

        if (dimSupplier != null) {
            row.setSupplier_id(dimSupplier.getId());
        } else {
            row.setSupplier_id(createSupplierEntry(row));
        }

        exchange.getOut().setBody(row);
    }

    private Integer createSupplierEntry(fullDataWithIDs row) {
        Integer lastId = jdbcTemplate.queryForObject("Select max(id) from dim_supplier", Integer.class);

        if (lastId == null) {
            lastId = 0;
        }

        dim_supplier dimSupplier = new dim_supplier(lastId + 1, row.getSupplier());

        supplierRepo.save(dimSupplier);

        return lastId + 1;
    }
}

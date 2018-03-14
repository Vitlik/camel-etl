package de.viadee.cameltest.Entities.Source.Repos;

import org.springframework.data.jpa.repository.JpaRepository;

import de.viadee.cameltest.Entities.Source.WarehouseAndRetailSales;
import de.viadee.cameltest.Entities.Source.WarehouseAndRetailSalesId;

public interface WarehouseAndRetailSalesRepository
        extends JpaRepository<WarehouseAndRetailSales, WarehouseAndRetailSalesId> {

}
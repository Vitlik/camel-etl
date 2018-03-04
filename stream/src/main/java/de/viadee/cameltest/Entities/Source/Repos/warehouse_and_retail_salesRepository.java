package de.viadee.cameltest.Entities.Source.Repos;

import org.springframework.data.jpa.repository.JpaRepository;

import de.viadee.cameltest.Entities.Source.warehouse_and_retail_sales;
import de.viadee.cameltest.Entities.Target.dim_supplier;

public interface warehouse_and_retail_salesRepository extends JpaRepository<warehouse_and_retail_sales, Long> {

}
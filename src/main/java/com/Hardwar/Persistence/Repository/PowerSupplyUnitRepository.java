package com.Hardwar.Persistence.Repository;

import com.Hardwar.Persistence.Entitys.PowerSupplyUnit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PowerSupplyUnitRepository extends JpaRepository<PowerSupplyUnit,Long> {
    List<PowerSupplyUnit> findAllByDomainName(String domainName);
    List<PowerSupplyUnit> findAllByPriceIsLessThanEqual(int amount);
}

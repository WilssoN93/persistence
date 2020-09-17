package com.Hardwar.Persistence.Repository;

import com.Hardwar.Persistence.Entitys.PowerSupplyUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PowerSupplyUnitRepository extends JpaRepository<PowerSupplyUnit,Long> {
    List<PowerSupplyUnit> findAllByDomainName(String domainName);
    List<PowerSupplyUnit> findAllByPriceIsLessThanEqual(int amount);
}

package com.Hardwar.Persistence.Repository;

import com.Hardwar.Persistence.Entitys.Chassi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChassiRepository extends JpaRepository<Chassi,Long> {

    List<Chassi> findAllByDomainName(String domainName);
    List<Chassi> findAllByPriceIsLessThanEqual(int amount);
}

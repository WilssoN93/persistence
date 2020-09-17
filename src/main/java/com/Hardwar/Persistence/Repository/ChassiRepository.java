package com.Hardwar.Persistence.Repository;

import com.Hardwar.Persistence.Entitys.Chassi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChassiRepository extends JpaRepository<Chassi,Long> {

    List<Chassi> findAllByDomainName(String domainName);
    List<Chassi> findAllByPriceIsLessThanEqual(int amount);

}

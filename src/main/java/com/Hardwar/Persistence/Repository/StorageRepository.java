package com.Hardwar.Persistence.Repository;

import com.Hardwar.Persistence.Entitys.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StorageRepository extends JpaRepository<Storage,Long> {

    List<Storage> findAllByDomainName(String domainName);
    List<Storage> findAllByPriceIsLessThanEqual(int amount);
    List<Storage> findAllByPriceIsLessThanEqualAndType(int amount, String type);
}

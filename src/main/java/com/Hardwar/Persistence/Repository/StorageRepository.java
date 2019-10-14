package com.Hardwar.Persistence.Repository;

import com.Hardwar.Persistence.Entitys.Storage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StorageRepository extends JpaRepository<Storage,Long> {

    List<Storage> findAllByDomainName(String domainName);
}

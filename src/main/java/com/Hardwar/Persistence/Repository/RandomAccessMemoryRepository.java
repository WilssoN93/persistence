package com.Hardwar.Persistence.Repository;

import com.Hardwar.Persistence.Entitys.RandomAccessMemory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RandomAccessMemoryRepository extends JpaRepository<RandomAccessMemory,Long> {
    List<RandomAccessMemory> findAllByDomainName(String domainName);
    List<RandomAccessMemory> findAllByPriceIsLessThanEqual(int amount);
}

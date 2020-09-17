package com.Hardwar.Persistence.Repository;

import com.Hardwar.Persistence.Entitys.RandomAccessMemory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RandomAccessMemoryRepository extends JpaRepository<RandomAccessMemory,Long> {
    List<RandomAccessMemory> findAllByDomainName(String domainName);
    List<RandomAccessMemory> findAllByPriceIsLessThanEqual(int amount);
}

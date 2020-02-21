package com.Hardwar.Persistence.Repository;

import com.Hardwar.Persistence.Entitys.MotherBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MotherBoardRepository extends JpaRepository<MotherBoard,Long> {
    List<MotherBoard> findAllByDomainName(String domainName);
    List<MotherBoard> findAllByPriceIsLessThanEqual(int amount);
    List<MotherBoard> findAllByPriceIsLessThanEqualAndAndSocket(int amount,String socket);
}

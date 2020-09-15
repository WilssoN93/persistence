package com.Hardwar.Persistence.Repository;

import com.Hardwar.Persistence.Entitys.Computer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ComputerRepository extends JpaRepository<Computer, Long> {


    Computer findComputerByBudgetAndDate(int budget, LocalDate date);
}

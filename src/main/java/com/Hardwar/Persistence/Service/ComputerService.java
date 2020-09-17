package com.Hardwar.Persistence.Service;

import com.Hardwar.Persistence.Entitys.Computer;
import com.Hardwar.Persistence.Repository.ComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ComputerService {

    @Autowired
    private ComputerRepository computerRepository;

    public Computer findComputerByBudgetAndDate(int budget, LocalDate date){
        return computerRepository.findComputerByBudgetAndDate(budget, date);
    }

    public Computer saveComputer(Computer computer){
        return computerRepository.save(computer);
    }
}

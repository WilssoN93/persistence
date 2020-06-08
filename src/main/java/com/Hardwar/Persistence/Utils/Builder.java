package com.Hardwar.Persistence.Utils;

import com.Hardwar.Persistence.Entitys.*;
import com.Hardwar.Persistence.Repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
@Service
public class Builder {

    @Autowired
    CentralProcessingUnitRepository cpuRepository;
    @Autowired
    MotherBoardRepository motherBoardRepository;
    @Autowired
    PowerSupplyUnitRepository powerSupplyUnitRepository;
    @Autowired
    RandomAccessMemoryRepository ramRepository;
    @Autowired
    StorageRepository storageRepository;
    @Autowired
    GraphicsCardRepository graphicsCardRepository;
    @Autowired
    ChassiRepository chassiRepository;

    List<Storage> storages;
    List<RandomAccessMemory> RAMs;
    List<CentralProcessingUnit> CPUs;
    List<GraphicsCard> GPUs;
    List<PowerSupplyUnit> PSUs;
    List<MotherBoard> motherBoards;
    List<Chassi> chassis;

    Computer computer;


    private Map<String, Double> budgetMap;
    private double remainder = 0;

    public Computer getAComputer(int budget) {
        computer = new Computer();
        budgetMap = new HashMap<>();
        setBudgetScaling(budget);
        this.buildComputer(budget);
        computer.setTotalPrice(computer.getTotalPrice());
        return computer;
    }

    private boolean getBestPossibleGraphicsCard(double amount) {
        GPUs = graphicsCardRepository.findAllByPriceIsLessThanEqual((int) amount);
        int bestScore = 0;
        GraphicsCard bestGraphicsCard = null;
        for (int i = 0; i < GPUs.size(); i++) {
            if (bestGraphicsCard != null) {
                int score = 0;
                if (GPUs.get(i).getBoostClock() == 0 && GPUs.get(i).getCudaCores() != 0) {
                    score = GPUs.get(i).getCoreClock() * GPUs.get(i).getCudaCores();
                } else if (bestGraphicsCard.getCudaCores() != 0) {
                    score = GPUs.get(i).getBoostClock() * GPUs.get(i).getCudaCores();
                }
                if (score > bestScore) {
                    bestGraphicsCard = GPUs.get(i);
                    bestScore = score;
                }
            } else {
                bestGraphicsCard = GPUs.get(i);
            }
        }
        if (bestGraphicsCard != null) {
            remainder = amount - bestGraphicsCard.getPrice();
            computer.setGpu(bestGraphicsCard);
            return true;
        }
        return false;
    }

    private boolean getBestPossibleCPU(double amount) {
        CPUs = cpuRepository.findAllByPriceIsLessThanEqual((int) (amount));
        CentralProcessingUnit bestCPU = null;
        for (int i = 0; i < CPUs.size(); i++) {
            double score = 0;
            if (bestCPU != null) {
                if (CPUs.get(i).getCoreClock() != null) {
                    if (bestCPU.getCoreClock() == null) {
                        score += 1;
                    } else if (Double.parseDouble(CPUs.get(i).getCoreClock()) == Double.parseDouble(bestCPU.getCoreClock())) {
                        score += 1;
                    } else if (Double.parseDouble(CPUs.get(i).getCoreClock()) > Double.parseDouble(bestCPU.getCoreClock())) {
                        score += 2;
                    }
                }
                if (CPUs.get(i).getBoostClock() != null) {
                    if (bestCPU.getBoostClock() == null) {
                        score += 1;
                    } else if (Double.parseDouble(CPUs.get(i).getBoostClock()) == Double.parseDouble(bestCPU.getBoostClock())) {
                        score += 1;
                    } else if (Double.parseDouble(CPUs.get(i).getBoostClock()) > Double.parseDouble(bestCPU.getBoostClock())) {
                        score += 2;
                    }
                }
                if (CPUs.get(i).getThreads() > bestCPU.getThreads()) {
                    score += 2;
                } else if (CPUs.get(i).getThreads() == bestCPU.getThreads()) {
                    score++;
                }
                if (CPUs.get(i).getCores() > bestCPU.getCores()) {
                    score += 2;
                } else if (CPUs.get(i).getCores() == bestCPU.getCores()) {
                    score++;
                }
                if (score >= 5) {
                    bestCPU = CPUs.get(i);
                }
            } else {
                bestCPU = CPUs.get(i);
            }

        }
        if (bestCPU != null) {
            remainder = amount - bestCPU.getPrice();
            computer.setCpu(bestCPU);
            return true;
        }
        return false;
    }

    private boolean getBestPossibleMotherBoard(double amount) {
        motherBoards = motherBoardRepository.findAllByPriceIsLessThanEqual((int) amount);
        MotherBoard bestMotherBoard = null;
        for (int i = 0; i < motherBoards.size(); i++) {
            int score = 0;
            if (motherBoards.get(i).getSupportedRam() != null && motherBoards.get(i).getSocket() != null && motherBoards.get(i).getFormFactor() != null) {
                if (motherBoards.get(i).getSocket().equals(computer.getCpu().getSocket())) {
                    if (bestMotherBoard != null) {
                        if (motherBoards.get(i).getMdot2() >= bestMotherBoard.getMdot2()) {
                            score++;
                        }
                        if (motherBoards.get(i).getSpeeds() >= bestMotherBoard.getSpeeds()) {
                            score++;
                        }
                        if (motherBoards.get(i).equals("ATX")) {
                            score++;
                        }
                        if (score >= 2) {
                            bestMotherBoard = motherBoards.get(i);
                        }
                    } else {
                        bestMotherBoard = motherBoards.get(i);
                    }
                }
            }
        }

        if (bestMotherBoard != null) {
            remainder = amount - bestMotherBoard.getPrice();
            computer.setMotherBoard(bestMotherBoard);

            return true;
        }
        return false;
    }

    private boolean getBestPossibleRAM(double amount) {
        RAMs = ramRepository.findAllByPriceIsLessThanEqual((int) amount);
        RandomAccessMemory bestRAM = null;
        for (int i = 0; i < RAMs.size(); i++) {
            if (RAMs.get(i).getDdr() != null && RAMs.get(i).getDdr().equals("DDR4")) {
                if (bestRAM != null) {
                    if (RAMs.get(i).getCapacity() > bestRAM.getCapacity()) {
                        bestRAM = RAMs.get(i);
                    }
                    if (RAMs.get(i).getCapacity() == bestRAM.getCapacity() && RAMs.get(i).getSpeeds() > bestRAM.getSpeeds()) {
                        bestRAM = RAMs.get(i);
                    }
                } else {
                    bestRAM = RAMs.get(i);
                }
            }
        }

        if (bestRAM != null) {
            remainder = amount - bestRAM.getPrice();
            computer.setRam(bestRAM);
            return true;
        }
        return false;
    }

    private boolean getBestPossibleStorage(double amount) {
        storages = storageRepository.findAllByPriceIsLessThanEqual((int) amount);
        Storage bestStorage = null;
        int score = 0;
        for (int i = 0; i < storages.size(); i++) {
            if (computer.getMotherBoard().getMdot2() <= 0 && storages.get(i).getFormFaktor().equals("M.2")) {
                storages.remove(storages.get(i));
                continue;
            }
            if (bestStorage != null) {
                int totalScore = storages.get(i).getSize() * storages.get(i).getReadSpeed();
                if (totalScore > score){
                    score = totalScore;
                    bestStorage = storages.get(i);
                }
            } else {
                bestStorage = storages.get(i);
            }

        }

        if (bestStorage != null) {
            remainder = amount - bestStorage.getPrice();
            computer.setStorage(bestStorage);
            return true;
        }
        return false;
    }

    private boolean getBestPossibleChassi(double amount) {
        chassis = chassiRepository.findAllByPriceIsLessThanEqual((int) amount);
        Chassi bestChassi = null;
        int score = 0;
        for (int i = 0; i < chassis.size(); i++) {
            if (computer.getMotherBoard().getFormFactor().equals(chassis.get(i).getFormFactor())) {
                if (bestChassi != null) {
                    if (chassis.get(i).getPrice() > bestChassi.getPrice()) {
                        score++;
                    }
                    if (score >= 1) {
                        bestChassi = chassis.get(i);
                    }
                } else {
                    bestChassi = chassis.get(i);
                }
            }
        }
        if (bestChassi != null) {
            remainder = amount - bestChassi.getPrice();
            computer.setChassi(bestChassi);
            return true;
        }
        return false;
    }

    private boolean getBestPossiblePSU(double amount) {
        PSUs = powerSupplyUnitRepository.findAllByPriceIsLessThanEqual((int) amount);
        PowerSupplyUnit bestPSU = null;
        int score = 0;
        for (int i = 0; i < PSUs.size(); i++) {
            if (PSUs.get(i).getCapacity() > computer.getGpu().getCapacity()) {
                if (bestPSU != null) {
                    if (PSUs.get(i).getFormFactor().equals(computer.getMotherBoard().getFormFactor())) {
                        if (PSUs.get(i).isModular()) {
                            score++;
                        }
                        if (PSUs.get(i).getCertPoints()>bestPSU.getCertPoints()){
                            score += 2;
                        }
                    }
                    if (score >= 3) {
                        bestPSU = PSUs.get(i);
                    }
                } else {
                    bestPSU = PSUs.get(i);
                }
            }
        }

        if (bestPSU != null) {
            remainder = amount - bestPSU.getPrice();
            computer.setPsu(bestPSU);
            return true;
        }
        return false;
    }


    private void buildComputer(int budget) {
        remainder = 0;

        if (!getBestPossibleGraphicsCard(budgetMap.get("GpuBudget") + remainder)) {
            increaseGraphicCardBudget();
            this.buildComputer(budget);
        }
        if (!getBestPossibleCPU(budgetMap.get("CpuBudget") + remainder)) {
            increaseCpuBudget();
            this.buildComputer(budget);
        }
        if (!getBestPossibleMotherBoard(budgetMap.get("MotherBoardBudget") + remainder)) {
            increaseMotherBoardBudget();
            this.buildComputer(budget);
        }
        if (!getBestPossibleRAM(budgetMap.get("RAMBudget") + remainder)) {
            increaseRAMBudget();
            this.buildComputer(budget);
        }
        if (!getBestPossibleStorage(budgetMap.get("StorageBudget") + remainder)) {
            increaseStorageBudget();
            this.buildComputer(budget);
        }
        if (!getBestPossibleChassi(budgetMap.get("chassiBudget") + remainder)) {
            increaseChassiBudget();
            this.buildComputer(budget);
        }
        if (!getBestPossiblePSU(budgetMap.get("PSUBudget") + remainder)) {
            increasePSUBudget();
            this.buildComputer(budget);
        }
    }

    private void increaseGraphicCardBudget() {
        double amount = budgetMap.get("GpuBudget");
        budgetMap.put("GpuBudget", budgetMap.get("GpuBudget") + amount * 0.06);
        budgetMap.put("CpuBudget", budgetMap.get("CpuBudget") - amount * 0.01);
        budgetMap.put("MotherBoardBudget", budgetMap.get("MotherBoardBudget") - amount * 0.01);
        budgetMap.put("RAMBudget", budgetMap.get("RAMBudget") - amount * 0.01);
        budgetMap.put("StorageBudget", budgetMap.get("StorageBudget") - amount * 0.01);
        budgetMap.put("chassiBudget", budgetMap.get("chassiBudget") - amount * 0.01);
        budgetMap.put("PSUBudget", budgetMap.get("PSUBudget") - amount * 0.01);
    }

    private void increaseCpuBudget() {
        double amount = budgetMap.get("CpuBudget");
        budgetMap.put("GpuBudget", budgetMap.get("GpuBudget") - amount * 0.01);
        budgetMap.put("CpuBudget", budgetMap.get("CpuBudget") + amount * 0.06);
        budgetMap.put("MotherBoardBudget", budgetMap.get("MotherBoardBudget") - amount * 0.01);
        budgetMap.put("RAMBudget", budgetMap.get("RAMBudget") - amount * 0.01);
        budgetMap.put("StorageBudget", budgetMap.get("StorageBudget") - amount * 0.01);
        budgetMap.put("chassiBudget", budgetMap.get("chassiBudget") - amount * 0.01);
        budgetMap.put("PSUBudget", budgetMap.get("PSUBudget") - amount * 0.01);

    }

    private void increaseMotherBoardBudget() {
        double amount = budgetMap.get("MotherBoardBudget");

        budgetMap.put("GpuBudget", budgetMap.get("GpuBudget") - amount * 0.01);
        budgetMap.put("CpuBudget", budgetMap.get("CpuBudget") - amount * 0.01);
        budgetMap.put("MotherBoardBudget", budgetMap.get("MotherBoardBudget") + amount * 0.06);
        budgetMap.put("RAMBudget", budgetMap.get("RAMBudget") - amount * 0.01);
        budgetMap.put("StorageBudget", budgetMap.get("StorageBudget") - amount * 0.01);
        budgetMap.put("chassiBudget", budgetMap.get("chassiBudget") - amount * 0.01);
        budgetMap.put("PSUBudget", budgetMap.get("PSUBudget") - amount * 0.01);
    }

    private void increaseRAMBudget() {
        double amount = budgetMap.get("RAMBudget");

        budgetMap.put("GpuBudget", budgetMap.get("GpuBudget") - amount * 0.01);
        budgetMap.put("CpuBudget", budgetMap.get("CpuBudget") - amount * 0.01);
        budgetMap.put("MotherBoardBudget", budgetMap.get("MotherBoardBudget") + amount * 0.01);
        budgetMap.put("RAMBudget", budgetMap.get("RAMBudget") + budgetMap.get("RAMBudget") * 0.06);
        budgetMap.put("StorageBudget", budgetMap.get("StorageBudget") - amount * 0.01);
        budgetMap.put("chassiBudget", budgetMap.get("chassiBudget") - amount * 0.01);
        budgetMap.put("PSUBudget", budgetMap.get("PSUBudget") - amount * 0.01);
    }

    private void increaseStorageBudget() {
        double amount = budgetMap.get("StorageBudget");

        budgetMap.put("GpuBudget", budgetMap.get("GpuBudget") - amount * 0.01);
        budgetMap.put("CpuBudget", budgetMap.get("CpuBudget") - amount * 0.01);
        budgetMap.put("MotherBoardBudget", budgetMap.get("MotherBoardBudget") + amount * 0.01);
        budgetMap.put("RAMBudget", budgetMap.get("RAMBudget") - amount * 0.01);
        budgetMap.put("StorageBudget", budgetMap.get("StorageBudget") + amount * 0.06);
        budgetMap.put("chassiBudget", budgetMap.get("chassiBudget") - amount * 0.01);
        budgetMap.put("PSUBudget", budgetMap.get("PSUBudget") - amount * 0.01);
    }

    private void increaseChassiBudget() {
        double amount = budgetMap.get("chassiBudget");

        budgetMap.put("GpuBudget", budgetMap.get("GpuBudget") - amount * 0.01);
        budgetMap.put("CpuBudget", budgetMap.get("CpuBudget") - amount * 0.01);
        budgetMap.put("MotherBoardBudget", budgetMap.get("MotherBoardBudget") + amount * 0.01);
        budgetMap.put("RAMBudget", budgetMap.get("RAMBudget") - amount * 0.01);
        budgetMap.put("StorageBudget", budgetMap.get("StorageBudget") - amount * 0.01);
        budgetMap.put("chassiBudget", budgetMap.get("chassiBudget") + amount * 0.06);
        budgetMap.put("PSUBudget", budgetMap.get("PSUBudget") - amount * 0.01);
    }

    private void increasePSUBudget() {
        double amount = budgetMap.get("PSUBudget");

        budgetMap.put("GpuBudget", budgetMap.get("GpuBudget") - amount * 0.01);
        budgetMap.put("CpuBudget", budgetMap.get("CpuBudget") - amount * 0.01);
        budgetMap.put("MotherBoardBudget", budgetMap.get("MotherBoardBudget") + amount * 0.01);
        budgetMap.put("RAMBudget", budgetMap.get("RAMBudget") - amount * 0.01);
        budgetMap.put("StorageBudget", budgetMap.get("StorageBudget") - amount * 0.01);
        budgetMap.put("chassiBudget", budgetMap.get("chassiBudget") - amount * 0.01);
        budgetMap.put("PSUBudget", budgetMap.get("PSUBudget") + amount * 0.06);
    }

    private void setBudgetScaling(int budget) {
        budgetMap.put("GpuBudget", budget * 0.42);
        budgetMap.put("CpuBudget", budget * 0.20);
        budgetMap.put("MotherBoardBudget", budget * 0.09);
        budgetMap.put("RAMBudget", budget * 0.10);
        budgetMap.put("StorageBudget", budget * 0.08);
        budgetMap.put("chassiBudget", budget * 0.05);
        budgetMap.put("PSUBudget", budget * 0.10);
    }


}

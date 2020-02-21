package com.Hardwar.Persistence.Utils;

import com.Hardwar.Persistence.Entitys.*;
import com.Hardwar.Persistence.Repository.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

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

    private double gpuModifier;
    private double cpuModifier;
    private double motherBoardModifier;
    private double ramModifier;
    private double storageModifier;
    private double chassiModifier;
    private final int HIGH = 18000;
    private final int MEDIUM_HIGH = 13000;
    private final int MEDIUM = 10000;
    private final int MEDIUM_LOW = 8000;
    private final int LOW = 5000;

    public Computer getAComputer(int budget) {
        computer = new Computer();
        verifiyBudgetScaling(budget);
        getBestPossibleGraphicsCard(budget);
        System.out.println(computer.toString());
        int totalPrice = computer.getGpu().getPrice() + computer.getMotherBoard().getPrice() + computer.getCpu().getPrice() + computer.getRam().getPrice() + computer.getChassi().getPrice() + computer.getPsu().getPrice() + computer.getStorage().getPrice();
        computer.setTotalPrice(totalPrice);
        return computer;
    }

    private int getBestPossibleGraphicsCard(int amount) {
        double gpuAmount = amount * gpuModifier;
        System.out.println(gpuAmount);
        GPUs = graphicsCardRepository.findAllByPriceIsLessThanEqual((int)gpuAmount);

        GraphicsCard bestGraphicsCard = null;
        for (int i = 0; i < GPUs.size(); i++) {
            if (bestGraphicsCard != null) {
                int score = 0;
                if (GPUs.get(i).getCoreClock() > bestGraphicsCard.getCoreClock()) {
                    score++;
                }
                if (GPUs.get(i).getBoostClock() > bestGraphicsCard.getBoostClock()) {
                    score++;
                }
                if (GPUs.get(i).getCudaCores() > bestGraphicsCard.getCudaCores()) {
                    score += 2;
                }
                if (score >= 3) {
                    bestGraphicsCard = GPUs.get(i);
                }
            } else {
                bestGraphicsCard = GPUs.get(i);
            }
        }
        if (bestGraphicsCard != null) {
            computer.setGpu(bestGraphicsCard);
            getBestPossibleCPU(amount - bestGraphicsCard.getPrice());
            return bestGraphicsCard.getPrice();
        }
        return 0;
    }

    private int getBestPossibleCPU(int amount) {
        double cpuAmount = amount * cpuModifier;
        CPUs = cpuRepository.findAllByPriceIsLessThanEqual((int)cpuAmount);
        CentralProcessingUnit bestCPU = null;
        for (int i = 0; i < CPUs.size(); i++) {
            double score = 0;
            if (bestCPU != null) {
                if (CPUs.get(i).getCoreClock() != null) {
                    if (bestCPU.getCoreClock()==null) {
                        score += 1;
                    }else if (Double.parseDouble(CPUs.get(i).getCoreClock()) == Double.parseDouble(bestCPU.getCoreClock())){
                        score+=1;
                    }else if(Double.parseDouble(CPUs.get(i).getCoreClock()) > Double.parseDouble(bestCPU.getCoreClock())){
                        score+=2;
                    }
                }
                if (CPUs.get(i).getBoostClock() != null) {
                    if (bestCPU.getBoostClock()==null) {
                        score+=1;
                    }else if (Double.parseDouble(CPUs.get(i).getBoostClock()) == Double.parseDouble(bestCPU.getBoostClock())){
                        score+=1;
                    }else if(Double.parseDouble(CPUs.get(i).getBoostClock()) > Double.parseDouble(bestCPU.getBoostClock())){
                        score+=2;
                    }
                }
                if (CPUs.get(i).getThreads() > bestCPU.getThreads()) {
                    score+=2;
                }
                else if (CPUs.get(i).getThreads() == bestCPU.getThreads()){
                    score++;
                }
                if (CPUs.get(i).getCores() > bestCPU.getCores()) {
                    score += 2;
                }else if (CPUs.get(i).getCores() == bestCPU.getCores()){
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
            computer.setCpu(bestCPU);
            getBestPossibleMotherBoard(amount - bestCPU.getPrice());
        }
        return 0;
    }

    private int getBestPossibleMotherBoard(int amount) {
        double motherboardAmount = amount * motherBoardModifier;
        motherBoards = motherBoardRepository.findAllByPriceIsLessThanEqual((int)motherboardAmount);
        MotherBoard bestMotherBoard = null;
        for (int i = 0; i < motherBoards.size(); i++) {
            int score = 0;
            if (motherBoards.get(i).getSupportedRam() != null && motherBoards.get(i).getSocket() != null && motherBoards.get(i).getFormFactor()!=null) {
                if (motherBoards.get(i).getSocket().equals(computer.getCpu().getSocket())) {
                    if (bestMotherBoard != null) {
                        if (motherBoards.get(i).getMdot2() >= bestMotherBoard.getMdot2()) {
                            score++;
                        }
                        if (motherBoards.get(i).getSpeeds()>=bestMotherBoard.getSpeeds()){
                            score++;
                        }
                        if (motherBoards.get(i).equals("ATX")){
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
            computer.setMotherBoard(bestMotherBoard);
            getBestPossibleRAM(amount - bestMotherBoard.getPrice());
            return bestMotherBoard.getPrice();
        }
        return 0;
    }

    private int getBestPossibleRAM(int amount) {
        double ramAmount = amount * ramModifier;
       RAMs = ramRepository.findAllByPriceIsLessThanEqual((int)ramAmount);
        RandomAccessMemory bestRAM = null;
        for (int i = 0; i < RAMs.size(); i++) {
            int score = 0;
          if(RAMs.get(i).getDdr() != null && RAMs.get(i).getDdr().equals("DDR4")) {
              if (bestRAM != null) {
                  if (RAMs.get(i).getSpeeds() > bestRAM.getSpeeds()) {
                      score++;
                  }
                  if (RAMs.get(i).getCapacity() > bestRAM.getCapacity()) {
                      score++;
                  }
                  if (score >= 2) {
                      bestRAM = RAMs.get(i);
                  }
              } else {
                      bestRAM = RAMs.get(i);
              }
          }
        }

        if (bestRAM != null) {
            computer.setRam(bestRAM);
            getBestPossibleStorage(amount - bestRAM.getPrice());
            return bestRAM.getPrice();
        }
        return 0;
    }

    private int getBestPossibleStorage(int amount) {
        double storageAmount = amount * storageModifier;
        storages = storageRepository.findAllByPriceIsLessThanEqual((int)storageAmount);
        Storage bestStorage = null;
        for (int i = 0; i < storages.size(); i++) {
            int score = 0;
            if (computer.getMotherBoard().getMdot2()<=0 && storages.get(i).getFormFaktor().equals("M.2")){
                storages.remove(storages.get(i));
                continue;
            }
            if (bestStorage != null) {
                if (storages.get(i).getType().equals("SSD")) {
                    score++;
                }
                if (storages.get(i).getSize() > bestStorage.getSize()) {
                    score++;
                }
                if (storages.get(i).getWriteSpeed() > bestStorage.getWriteSpeed()) {
                    score++;
                }
                if (storages.get(i).getReadSpeed() > bestStorage.getReadSpeed()) {
                    score++;
                }
                if (score >= 3) {
                    bestStorage = storages.get(i);
                }
            } else {
                bestStorage = storages.get(i);
            }

        }

        if (bestStorage != null) {
            computer.setStorage(bestStorage);
            getBestPossibleChassi(amount - bestStorage.getPrice());
            return bestStorage.getPrice();
        }
        return 0;
    }

    private int getBestPossibleChassi(int amount) {
        double chassiAmount = amount * chassiModifier;

        chassis = chassiRepository.findAllByPriceIsLessThanEqual((int)chassiAmount);
        Chassi bestChassi = null;
        int score = 0;
        for (int i = 0; i < chassis.size(); i++) {
                if (computer.getMotherBoard().getFormFactor().equals(chassis.get(i).getFormFactor())) {
                    if (bestChassi != null) {
                        if(chassis.get(i).getPrice()>bestChassi.getPrice()){
                            score++;
                        }
                        if (score >= 1){
                            bestChassi = chassis.get(i);
                        }
                    } else {
                        bestChassi = chassis.get(i);
                    }
            }
        }
        if (bestChassi != null) {
            computer.setChassi(bestChassi);
            getBestPossiblePSU(amount - bestChassi.getPrice());
            return bestChassi.getPrice();
        }
        return 0;
    }

    private int getBestPossiblePSU(int amount) {
        double psuAmount = amount;
        PSUs = powerSupplyUnitRepository.findAllByPriceIsLessThanEqual((int)psuAmount);
        PowerSupplyUnit bestPSU = null;
        int score = 0;
        for (int i = 0; i < PSUs.size(); i++) {
            if (PSUs.get(i).getCapacity() > computer.getGpu().getCapacity()) {
                if (bestPSU != null) {
                    if (PSUs.get(i).isModular()) {
                        score++;
                    }
                    if (PSUs.get(i).getFormFactor().equals(computer.getMotherBoard().getFormFactor())) {
                        score += 2;
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
            computer.setPsu(bestPSU);
            return bestPSU.getPrice();
        }
        return 0;
    }


    private void verifiyBudgetScaling(int budget) {
        if (budget <= HIGH && budget > MEDIUM_HIGH) {
            gpuModifier = 0.50;
            cpuModifier = 0.50;
            motherBoardModifier = 0.40;
            ramModifier = 0.40;
            storageModifier = 0.50;
            chassiModifier = 0.70;
        } else if (budget <= MEDIUM_HIGH && budget > MEDIUM) {
            gpuModifier = 0.40;
            cpuModifier = 0.40;
            motherBoardModifier = 0.40;
            ramModifier = 0.40;
            storageModifier = 0.40;
            chassiModifier = 0.70;
        } else if (budget <= MEDIUM && budget > MEDIUM_LOW) {
            gpuModifier = 0.35;
            cpuModifier = 0.35;
            motherBoardModifier = 0.30;
            ramModifier = 0.35;
            storageModifier = 0.50;
            chassiModifier = 0.60;
        } else if (budget <= MEDIUM_LOW && budget > LOW) {
            gpuModifier = 0.35;
            cpuModifier = 0.35;
            motherBoardModifier = 0.30;
            ramModifier = 0.35;
            storageModifier = 0.30;
            chassiModifier = 0.60;
        }else if(budget <= LOW){
            gpuModifier = 0.15;
            cpuModifier = 0.20;
            motherBoardModifier = 0.30;
            ramModifier = 0.30;
            storageModifier = 0.30;
            chassiModifier = 0.60;
        }else if(budget > HIGH){
            gpuModifier = 0.60;
            cpuModifier = 0.40;
            motherBoardModifier = 0.40;
            ramModifier = 0.40;
            storageModifier = 0.50;
            chassiModifier = 0.70;
        }

    }


}

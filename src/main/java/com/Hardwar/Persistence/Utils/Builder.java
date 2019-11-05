package com.Hardwar.Persistence.Utils;

import com.Hardwar.Persistence.Entitys.*;
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
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class Builder {

    CloseableHttpClient client;
    HttpGet get;
    String json;
    Gson gson = new Gson();
    List<Storage> storages;
    List<RandomAccessMemory> RAMs;
    List<CentralProcessingUnit> CPUs;
    List<GraphicsCard> GPUs;
    List<PowerSupplyUnit> PSUs;
    List<MotherBoard> motherBoards;
    List<Chassi> chassis;
    Computer computer;
    int budget;

    public Builder() {
        computer = new Computer();
    }


    public Computer getAComputer(int budget) {
        this.budget = budget;

        budget = budget - getBestPossibleRAM((int) (this.budget * 0.10));
        budget = budget - getBestPossibleStorage((int)(this.budget * 0.10));
        budget = budget - getBestPossibleMotherBoard((int)(this.budget * 0.15));
        budget = budget - getBestPossibleChassi((int)(this.budget * 0.10));
        budget = budget - getBestPossibleCPU((int)(this.budget * 0.20));
        budget = budget - getBestPossibleGraphicsCard((int)(this.budget * 0.25));
        computer.setTotalPrice(this.budget - budget);

        return computer;
    }

    public List<? extends ComputerComponent> getComponents(String host, String endpoint, Type type, int amount) {

        client = HttpClients.createDefault();
        List<? extends ComputerComponent> components = new ArrayList<>();
        Type component = type;
        try {
            get = new HttpGet(host + "/" + endpoint + "/price/" + amount);
            System.out.println("Executing " + get.getRequestLine());
            get.addHeader("Content-Type", "application/json");
            get.addHeader("Accept", "application/json;charset=UTF-8");
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
                @Override
                public String handleResponse(HttpResponse httpResponse) throws IOException {
                    int statusCode = httpResponse.getStatusLine().getStatusCode();
                    if (statusCode >= 200 && statusCode < 300) {
                        HttpEntity entity = httpResponse.getEntity();
                        json = EntityUtils.toString(entity);

                        return json;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + statusCode);
                    }
                }
            };
            String response = client.execute(get, responseHandler);
            components = gson.fromJson(response, component);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return components;

    }

    private int getBestPossibleGraphicsCard(int amount) {
        GPUs = (List<GraphicsCard>) getComponents("http://localhost:8080", "graphicscard", new TypeToken<List<GraphicsCard>>() {
        }.getType(), amount);

        GraphicsCard bestGraphicsCard = null;
        for (int i = 0; i < GPUs.size(); i++) {
            if (bestGraphicsCard != null) {
                int score = 0;
                if (GPUs.get(i).getCoreClock() > bestGraphicsCard.getCoreClock()) {
                    score += 2;
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
        }
        return bestGraphicsCard.getPrice();
    }

    private int getBestPossibleCPU(int amount) {
        CPUs = (List<CentralProcessingUnit>) getComponents("http://localhost:8080", "cpu", new TypeToken<List<CentralProcessingUnit>>() {
        }.getType(), amount);
        CentralProcessingUnit bestCPU = null;
        for (int i = 0; i < CPUs.size(); i++) {
            int score = 0;
            if (bestCPU != null) {
                if (computer.getMotherBoard().getSocket().equals(bestCPU.getSocket())) {
                    if (CPUs.get(i).getCoreClock() != null && bestCPU.getCoreClock() != null) {
                        if (Double.parseDouble(CPUs.get(i).getCoreClock()) > Double.parseDouble(bestCPU.getCoreClock())) {
                            score += 2;
                        }
                    }
                    if (CPUs.get(i).getBoostClock() != null && bestCPU.getBoostClock() != null) {
                        if (Double.parseDouble(CPUs.get(i).getBoostClock()) > Double.parseDouble(bestCPU.getBoostClock())) {
                            score++;
                        }
                    }
                    if (CPUs.get(i).getThreads() > bestCPU.getThreads()) {
                        score++;
                    }
                    if (CPUs.get(i).getCores() > bestCPU.getCores()) {
                        score++;
                    }
                    if (score >= 3) {
                        bestCPU = CPUs.get(i);
                    }
                }
            } else {
                bestCPU = CPUs.get(i);
            }

            if (bestCPU != null) {
                computer.setCpu(bestCPU);
            }
        }
        return bestCPU.getPrice();
    }

    private int getBestPossibleRAM(int amount) {
        RAMs = (List<RandomAccessMemory>) getComponents("http://localhost:8080", "ram", new TypeToken<List<RandomAccessMemory>>() {
        }.getType(), amount);
        RandomAccessMemory bestRAM = null;
        for (int i = 0; i < RAMs.size(); i++) {
            int score = 0;

            if (bestRAM != null) {
                if (RAMs.get(i).getSpeeds() > bestRAM.getSpeeds()) {
                    score++;
                }
                if (RAMs.get(i).getCapacity() > bestRAM.getCapacity()) {
                    score++;
                }
                if (RAMs.get(i).getDdr() != null) {
                    if (RAMs.get(i).getDdr().equals("DD4")) {
                        score++;
                    }
                } else {
                    RAMs.remove(RAMs.get(i));
                }
                if (score >= 2) {
                    bestRAM = RAMs.get(i);
                }
            } else {
                if (RAMs.get(i).getDdr() != null && !RAMs.get(i).getDdr().contains("SODIMM")) {
                    bestRAM = RAMs.get(i);
                }
            }
        }

        if (bestRAM != null) {
            computer.setRam(bestRAM);
        }
        return bestRAM.getPrice();
    }

    private int getBestPossibleMotherBoard(int amount) {
        motherBoards = (List<MotherBoard>) getComponents("http://localhost:8080", "motherboards", new TypeToken<List<MotherBoard>>() {
        }.getType(), amount);
        MotherBoard bestMotherBoard = null;
        int score = 0;
        for (int i = 0; i < motherBoards.size(); i++) {
            if (motherBoards.get(i).getSupportedRam() != null || motherBoards.get(i).getSocket() != null) {
                if (bestMotherBoard != null) {
                    if (motherBoards.get(i).getMdot2() > bestMotherBoard.getMdot2()) {
                        score++;
                    }
                    if (score == 1) {
                        bestMotherBoard = motherBoards.get(i);
                    }
                } else {
                    bestMotherBoard = motherBoards.get(i);
                }
            }
        }

        if (bestMotherBoard != null) {
            computer.setMotherBoard(bestMotherBoard);
        }
        return bestMotherBoard.getPrice();
    }

    private int getBestPossibleChassi(int amount) {
        chassis = (List<Chassi>) getComponents("http://localhost:8080", "chassi", new TypeToken<List<Chassi>>() {
        }.getType(), amount);
        Chassi bestChassi = null;
        int score = 0;
        for (int i = 0; i < chassis.size(); i++) {

            if (computer.getMotherBoard().getFormFactor().equals(chassis.get(i).getFormFactor())) {
                if (bestChassi != null) {
                    if (chassis.get(i).getPrice() > bestChassi.getPrice()) {
                        score++;
                    }
                } else {
                    bestChassi = chassis.get(i);
                }
            }
        }
        if (bestChassi != null) {
            computer.setChassi(bestChassi);
        }

        return bestChassi.getPrice();
    }

    private int getBestPossibleStorage(int amount) {
        storages = (List<Storage>) getComponents("http://localhost:8080", "storage", new TypeToken<List<Storage>>() {
        }.getType(), amount);
        Storage bestStorage = null;
        int score = 0;
        for (int i = 0; i < storages.size(); i++) {
            if (bestStorage != null) {
                if (storages.get(i).getType().equals("SSD")) {
                    score++;
                }
                if (storages.get(i).getSize() > bestStorage.getSize()) {
                    score += 2;
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
        }
        return bestStorage.getPrice();
    }

    private int getBestPossiblePSU(int amount){

        PSUs = (List<PowerSupplyUnit>) getComponents("http://localhost:8080", "psu", new TypeToken<List<PowerSupplyUnit>>() {
        }.getType(), amount);
        PowerSupplyUnit bestPSU = null;
        int score = 0;
        for (int i = 0; i < PSUs.size(); i++) {
            if (bestPSU != null) {
                if (PSUs.get(i).isModular()) {
                    score++;
                }
                if (PSUs.get(i).getFormFactor().equals(computer.getMotherBoard().getFormFactor())) {
                    score += 2;
                }
                if (PSUs.get(i).getCapacity() > computer.getGpu().getCapacity()) {
                    score++;
                }
                if (score >= 3) {
                    bestPSU = PSUs.get(i);
                }
            } else {
                bestPSU = PSUs.get(i);
            }

        }


        return 0;
    }


}

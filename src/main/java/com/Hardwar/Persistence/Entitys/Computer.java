package com.Hardwar.Persistence.Entitys;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Computer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int budget;

    @ManyToOne
    private GraphicsCard gpu;
    @ManyToOne
    private CentralProcessingUnit cpu;
    @ManyToOne
    private RandomAccessMemory ram;
    @ManyToOne
    private Chassi chassi;
    @ManyToOne
    private Storage storage;
    @ManyToOne
    private MotherBoard motherBoard;
    @ManyToOne
    private PowerSupplyUnit psu;

    private LocalDate date;

    private int totalPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getBudget() {
        return budget;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getTotalPrice() {

        if (this.isComplete()) {
            return this.getGpu().getPrice() + this.getMotherBoard().getPrice() + this.getCpu().getPrice() + this.getRam().getPrice() + this.getChassi().getPrice() + this.getPsu().getPrice() + this.getStorage().getPrice();
        } else return 0;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public GraphicsCard getGpu() {
        return gpu;
    }

    public void setGpu(GraphicsCard gpu) {
        this.gpu = gpu;
    }

    public CentralProcessingUnit getCpu() {
        return cpu;
    }

    public void setCpu(CentralProcessingUnit cpu) {
        this.cpu = cpu;
    }

    public RandomAccessMemory getRam() {
        return ram;
    }

    public void setRam(RandomAccessMemory ram) {
        this.ram = ram;
    }

    public Chassi getChassi() {
        return chassi;
    }

    public void setChassi(Chassi chassi) {
        this.chassi = chassi;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public MotherBoard getMotherBoard() {
        return motherBoard;
    }

    public void setMotherBoard(MotherBoard motherBoard) {
        this.motherBoard = motherBoard;
    }

    public PowerSupplyUnit getPsu() {
        return psu;
    }

    public void setPsu(PowerSupplyUnit psu) {
        this.psu = psu;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "gpu=" + gpu +
                ", cpu=" + cpu +
                ", ram=" + ram +
                ", chassi=" + chassi +
                ", storage=" + storage +
                ", motherBoard=" + motherBoard +
                ", psu=" + psu +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public boolean isComplete() {
        if (this.gpu == null || this.cpu == null || this.chassi == null || this.storage == null || this.motherBoard == null || this.ram == null || this.psu == null) {
            return false;
        } else {
            return true;
        }
    }
}

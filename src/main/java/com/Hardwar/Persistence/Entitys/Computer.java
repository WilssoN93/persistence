package com.Hardwar.Persistence.Entitys;

public class Computer {

    private GraphicsCard gpu;
    private CentralProcessingUnit cpu;
    private RandomAccessMemory ram;
    private Chassi chassi;
    private Storage storage;
    private MotherBoard motherBoard;
    private PowerSupplyUnit psu;
    private int totalPrice;

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

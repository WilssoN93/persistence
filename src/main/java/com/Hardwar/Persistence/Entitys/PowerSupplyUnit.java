package com.Hardwar.Persistence.Entitys;

import javax.persistence.*;

@Entity
@Table(name = "powersupplyunit")
public class PowerSupplyUnit extends ComputerComponent {

    String articleNumber;
    String domainName;
    String imgUrl;
    String formFactor;
    int certPoints;
    String gpuConnection;
    int capacity;

    @Column(name = "certpoints")
    public int getCertPoints() {
        return certPoints;
    }

    public void setCertPoints(int certPoints) {
        this.certPoints = certPoints;
    }

    @Column(name = "articlenumber")
    public String getArticleNumber() {
        return articleNumber;
    }

    @Column(name = "formfactor")
    public String getFormFactor() {
        return formFactor;
    }

    @Column(name = "capacity")
    public int getCapacity() {
        return capacity;
    }

    @Column(name = "modular")
    public boolean isModular() {
        return modular;
    }

    @Column(name = "imgurl")
    public String getImgUrl() {
        return imgUrl;
    }

    @Column(name = "domainname")
    public String getDomainName() {
        return domainName;
    }

    @Column(name  = "gpuconnection")
    public String getGpuConnection() {
        return gpuConnection;
    }

    public void setGpuConnection(String gpuConnection) {
        this.gpuConnection = gpuConnection;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public void setArticleNumber(String articleNumber) {
        this.articleNumber = articleNumber;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setModular(boolean modular) {
        this.modular = modular;
    }

    boolean modular;

    @Override
    public String toString() {
        return "PowerSupplyUnit{" +
                "articleNumber='" + articleNumber + '\'' +
                ", domainName='" + domainName + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", formFactor='" + formFactor + '\'' +
                ", certPoints=" + certPoints +
                ", gpuConnection='" + gpuConnection + '\'' +
                ", capacity=" + capacity +
                ", modular=" + modular +
                '}';
    }
}

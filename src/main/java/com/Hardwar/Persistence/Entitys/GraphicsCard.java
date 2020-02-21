package com.Hardwar.Persistence.Entitys;

import javax.persistence.*;

@Entity
@Table(name = "graphicscard")
public class GraphicsCard extends ComputerComponent {


    String imgUrl;
    String articleNumber;
    int coreClock;
    int boostClock;
    int cudaCores;
    String connection;
    String domainName;
    int capacity;

    @Column(name = "capacity")
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Column(name = "domainname")
    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }


    @Column(name = "imgurl")
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Column(name = "articlenumber")
    public String getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(String articleNumber) {
        this.articleNumber = articleNumber;
    }

    @Column(name = "coreclock")
    public int getCoreClock() {
        return coreClock;
    }

    public void setCoreClock(int coreClock) {
        this.coreClock = coreClock;
    }

    @Column(name = "boostclock")
    public int getBoostClock() {
        return boostClock;
    }

    public void setBoostClock(int boostClock) {
        this.boostClock = boostClock;
    }

    @Column(name = "cudacores")
    public int getCudaCores() {
        return cudaCores;
    }

    public void setCudaCores(int cudaCores) {
        this.cudaCores = cudaCores;
    }

    @Column(name = "connection")
    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

    @Override
    public String toString() {
        return "GraphicsCard{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", imgUrl='" + imgUrl + '\'' +
                ", articleNumber='" + articleNumber + '\'' +
                ", coreClock=" + coreClock +
                ", boostClock=" + boostClock +
                ", cudaCores=" + cudaCores +
                ", connection='" + connection + '\'' +
                ", domainName='" + domainName + '\'' +
                ", capacity=" + capacity +
                '}';
    }


}

package com.Hardwar.Persistence.Entitys;


import javax.persistence.*;

@Entity
@Table(name = "processor")
public class CentralProcessingUnit extends ComputerComponent {


    String domain;
    String imgUrl;
    String articleNumber;
    String socket;
    String coreClock;
    String boostClock;
    int threads;
    int cores;

    @Column(name = "domain")
    public String getDomain() {
        return domain;
    }

    @Column(name = "imgurl")
    public String getImgUrl() {
        return imgUrl;
    }

    @Column(name = "articlenumber")
    public String getArticleNumber() {
        return articleNumber;
    }

    @Column(name = "socket")
    public String getSocket() {
        return socket;
    }

    @Column(name = "coreclock")
    public String getCoreClock() {
        return coreClock;
    }

    @Column(name = "boostclock")
    public String getBoostClock() {
        return boostClock;
    }

    @Column(name = "threads")
    public int getThreads() {
        return threads;
    }

    @Column(name = "cores")
    public int getCores() {
        return cores;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setArticleNumber(String articleNumber) {
        this.articleNumber = articleNumber;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public void setCoreClock(String coreClock) {
        this.coreClock = coreClock;
    }

    public void setBoostClock(String boostClock) {
        this.boostClock = boostClock;
    }

    public void setThreads(int threads) {
        this.threads = threads;
    }


    public void setCores(int cores) {
        this.cores = cores;
    }

    @Override
    public String toString() {
        return "CentralProcessingUnit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", url='" + url + '\'' +
                ", domain='" + domain + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", articleNumber='" + articleNumber + '\'' +
                ", socket='" + socket + '\'' +
                ", coreClock='" + coreClock + '\'' +
                ", boostClock='" + boostClock + '\'' +
                ", threads='" + threads + '\'' +
                ", cores='" + cores + '\'' +
                '}';
    }
}

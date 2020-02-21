package com.Hardwar.Persistence.Entitys;

import javax.persistence.*;

@Entity
@Table(name = "ram")
public class RandomAccessMemory extends ComputerComponent {


    String domainName;
    String articleNumber;
    String ddr;
    int speeds;
    int capacity;
    String imgUrl;



    @Column(name = "domainname")
    public String getDomainName() {
        return domainName;
    }

    @Column(name = "articlenumber")
    public String getArticleNumber() {
        return articleNumber;
    }

    @Column(name = "ddr")
    public String getDdr() {
        return ddr;
    }

    @Column(name = "speeds")
    public int getSpeeds() {
        return speeds;
    }

    @Column(name = "capacity")
    public int getCapacity() {
        return capacity;
    }

    @Column(name = "imgurl")
    public String getImgUrl() {
        return imgUrl;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public void setArticleNumber(String articleNumber) {
        this.articleNumber = articleNumber;
    }

    public void setDdr(String ddr) {
        this.ddr = ddr;
    }

    public void setSpeeds(int speeds) {
        this.speeds = speeds;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "RandomAccessMemory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", domainName='" + domainName + '\'' +
                ", price='" + price + '\'' +
                ", articleNumber='" + articleNumber + '\'' +
                ", ddr='" + ddr + '\'' +
                ", speeds='" + speeds + '\'' +
                ", capacity='" + capacity + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}

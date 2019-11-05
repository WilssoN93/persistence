package com.Hardwar.Persistence.Entitys;

import javax.persistence.*;

@Entity
@Table(name = "graphicscard")
public class GraphicsCard extends ComputerComponent {

    Long id;
    String url;
    String name;

     @Column(name = "url")
    public String getUrl() {
        return url;
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

    public void setUrl(String url) {
        this.url = url;
    }

    int price;
    String imgUrl;
    String articleNumber;
    int coreClock;
    int boostClock;
    int cudaCores;

    @Column(name = "capacity")
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    String connection;
    String domainName;
    int capacity;

    @Column(name = "domainname")
    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "`name`")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "price")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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

}

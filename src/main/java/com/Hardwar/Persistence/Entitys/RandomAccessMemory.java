package com.Hardwar.Persistence.Entitys;

import javax.persistence.*;

@Entity
@Table(name = "ram")
public class RandomAccessMemory extends ComputerComponent {

    Long id;
    String name;
    String domainName;
    String price;
    String articleNumber;
    String ddr;
    String speeds;
    String capacity;
    String url;
    String imgUrl;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }
    @Column(name = "`name`")
    public String getName() {
        return name;
    }
    @Column(name = "domainname")
    public String getDomainName() {
        return domainName;
    }
    @Column(name = "price")
    public String getPrice() {
        return price;
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
    public String getSpeeds() {
        return speeds;
    }
    @Column(name = "capacity")
    public String getCapacity() {
        return capacity;
    }
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    @Column(name = "imgurl")
    public String getImgUrl() {
        return imgUrl;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setArticleNumber(String articleNumber) {
        this.articleNumber = articleNumber;
    }

    public void setDdr(String ddr) {
        this.ddr = ddr;
    }

    public void setSpeeds(String speeds) {
        this.speeds = speeds;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

}

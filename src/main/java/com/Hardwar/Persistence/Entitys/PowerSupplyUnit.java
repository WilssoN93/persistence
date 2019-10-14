package com.Hardwar.Persistence.Entitys;

import javax.persistence.*;

@Entity
@Table(name = "powersupplyunit")
public class PowerSupplyUnit extends ComputerComponent {

    Long id;
    String name;
    String price;
    String articleNumber;
    String domainName;
    String url;
    String imgUrl;
    String formFactor;
    String capacity;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    @Column(name = "articlenumber")
    public String getArticleNumber() {
        return articleNumber;
    }

    @Column(name = "price")
    public String getPrice() {
        return price;
    }

    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    @Column(name = "`name`")
    public String getName() {
        return name;
    }

    @Column(name = "formfactor")
    public String getFormFactor() {
        return formFactor;
    }

    @Column(name = "capacity")
    public String getCapacity() {
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

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setArticleNumber(String articleNumber) {
        this.articleNumber = articleNumber;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public void setModular(boolean modular) {
        this.modular = modular;
    }

    boolean modular;


}

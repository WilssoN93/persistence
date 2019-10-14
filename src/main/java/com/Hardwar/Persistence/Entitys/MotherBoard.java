package com.Hardwar.Persistence.Entitys;

import javax.persistence.*;

@Entity
@Table(name = "motherboard")
public class MotherBoard extends ComputerComponent {

    Long id;
    String name;
    String price;
    String domainName;
    String articleNumber;
    String url;
    String imgUrl;
    String socket;
    String formFactor;
    String supportedRam;
    String speeds;


    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }
    @Column(name = "speeds")
    public String getSpeeds() {
        return speeds;
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
    @Column(name = "url")
    public String getUrl() {
        return url;
    }
    @Column(name = "imgurl")
    public String getImgUrl() {
        return imgUrl;
    }
    @Column(name = "socket")
    public String getSocket() {
        return socket;
    }
    @Column(name = "formfactor")
    public String getFormFactor() {
        return formFactor;
    }
    @Column(name = "supportedram")
    public String getSupportedRam() {
        return supportedRam;
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

    public void setDomainName(String domainName) {
        this.domainName = domainName;
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

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public void setSpeeds(String speeds) {
        this.speeds = speeds;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

    public void setSupportedRam(String supportedRam) {
        this.supportedRam = supportedRam;
    }



}

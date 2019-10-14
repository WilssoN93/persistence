package com.Hardwar.Persistence.Entitys;

import javax.persistence.*;

@Entity
@Table(name = "storage")
public class Storage extends ComputerComponent {

    Long id;
    String name;
    String price;
    String articleNumber;
    String domainName;
    String url;
    String imgUrl;
    String size;
    String type;
    String readSpeed;
    String writeSpeed;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    @Column(name = "`name`")
    public String getName() {
        return name;
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

    @Column(name = "type")
    public String getType() {
        return type;
    }

    @Column(name = "size")
    public String getSize() {
        return size;
    }

    @Column(name = "writespeed")
    public String getWriteSpeed() {
        return writeSpeed;
    }

    @Column(name = "readspeed")
    public String getReadSpeed() {
        return readSpeed;
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

    public void setSize(String size) {
        this.size = size;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setReadSpeed(String readSpeed) {
        this.readSpeed = readSpeed;
    }

    public void setWriteSpeed(String writeSpeed) {
        this.writeSpeed = writeSpeed;
    }
}

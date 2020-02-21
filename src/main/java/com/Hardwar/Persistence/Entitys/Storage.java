package com.Hardwar.Persistence.Entitys;

import javax.persistence.*;

@Entity
@Table(name = "storage")
public class Storage extends ComputerComponent {

    String articleNumber;
    String domainName;
    String imgUrl;
    int size;
    String type;
    int readSpeed;
    int writeSpeed;
    String formFaktor;
    String mdot2key;
    String mdot2size;


    @Column(name = "articlenumber")
    public String getArticleNumber() {
        return articleNumber;
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
    public int getSize() {
        return size;
    }

    @Column(name = "writespeed")
    public int getWriteSpeed() {
        return writeSpeed;
    }

    @Column(name = "readspeed")
    public int getReadSpeed() {
        return readSpeed;
    }

    @Column(name = "domainname")
    public String getDomainName() {
        return domainName;
    }

    @Column(name = "formfaktor")
    public String getFormFaktor() {
        return formFaktor;
    }

    @Column(name = "mdot2key")
    public String getMdot2key() {
        return mdot2key;
    }

    @Column(name = "mdot2size")
    public String getMdot2size() {
        return mdot2size;
    }

    public void setMdot2key(String mdot2key) {
        this.mdot2key = mdot2key;
    }

    public void setMdot2size(String mdot2size) {
        this.mdot2size = mdot2size;
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

    public void setSize(int size) {
        this.size = size;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setReadSpeed(int readSpeed) {
        this.readSpeed = readSpeed;
    }

    public void setWriteSpeed(int writeSpeed) {
        this.writeSpeed = writeSpeed;
    }

    public void setFormFaktor(String formFaktor) {
        this.formFaktor = formFaktor;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", articleNumber='" + articleNumber + '\'' +
                ", domainName='" + domainName + '\'' +
                ", url='" + url + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", size='" + size + '\'' +
                ", type='" + type + '\'' +
                ", readSpeed='" + readSpeed + '\'' +
                ", writeSpeed='" + writeSpeed + '\'' +
                '}';
    }
}

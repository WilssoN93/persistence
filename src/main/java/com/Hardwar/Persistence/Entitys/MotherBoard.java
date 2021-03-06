package com.Hardwar.Persistence.Entitys;

import javax.persistence.*;

@Entity
@Table(name = "motherboard")
public class MotherBoard extends ComputerComponent {

    String domainName;
    String articleNumber;
    String imgUrl;
    String socket;
    String formFactor;
    String supportedRam;
    int speeds;
    int mdot2;
    String mdot2Size;
    String mdot2Key;




    @Column(name = "speeds")
    public int getSpeeds() {
        return speeds;
    }

    @Column(name = "domainname")
    public String getDomainName() {
        return domainName;
    }

    @Column(name = "articlenumber")
    public String getArticleNumber() {
        return articleNumber;
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

    @Column(name = "mdot2")
    public int getMdot2() {
        return mdot2;
    }

    @Column(name = "mdot2size")
    public String getMdot2Size() {
        return mdot2Size;
    }

    @Column(name = "mdot2key")
    public String getMdot2Key() {
        return mdot2Key;
    }

    public void setMdot2(int mdot2) {
        this.mdot2 = mdot2;
    }

    public void setMdot2Size(String mdot2Size) {
        this.mdot2Size = mdot2Size;
    }

    public void setMdot2Key(String mdot2Key) {
        this.mdot2Key = mdot2Key;
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

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public void setSpeeds(int speeds) {
        this.speeds = speeds;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

    public void setSupportedRam(String supportedRam) {
        this.supportedRam = supportedRam;
    }

    @Override
    public String toString() {
        return "MotherBoard{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", domainName='" + domainName + '\'' +
                ", articleNumber='" + articleNumber + '\'' +
                ", url='" + url + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", socket='" + socket + '\'' +
                ", formFactor='" + formFactor + '\'' +
                ", supportedRam='" + supportedRam + '\'' +
                ", speeds='" + speeds + '\'' +
                '}';
    }
}

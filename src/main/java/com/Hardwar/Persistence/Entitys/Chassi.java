package com.Hardwar.Persistence.Entitys;

import javax.persistence.*;

@Entity
@Table(name = "chassi")
public class Chassi extends ComputerComponent {

    Long id;
    String name;
    int price;
    String articleNumber;
    String domainName;
    String url;
    String imgUrl;
    String formFactor;
    String maxHeightCpuCooler;
    String maxGpuWidth;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    @Column(name = "price")
    public int getPrice() {
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

    @Column(name = "`name`")
    public String getName() {
        return name;
    }

    @Column(name = "formfactor")
    public String getFormFactor() {
        return formFactor;
    }

    @Column(name = "maxgpuwidth")
    public String getMaxGpuWidth() {
        return maxGpuWidth;
    }

    @Column(name = "maxheightcpucooler")
    public String getMaxHeightCpuCooler() {
        return maxHeightCpuCooler;
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

    public void setPrice(int price) {
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

    public void setMaxHeightCpuCooler(String maxHeightCpuCooler) {
        this.maxHeightCpuCooler = maxHeightCpuCooler;
    }

    public void setMaxGpuWidth(String maxGpuWidth) {
        this.maxGpuWidth = maxGpuWidth;
    }

    @Override
    public String toString() {
        return "Chassi{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", articleNumber='" + articleNumber + '\'' +
                ", domainName='" + domainName + '\'' +
                ", url='" + url + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", formFactor='" + formFactor + '\'' +
                ", maxHeightCpuCooler='" + maxHeightCpuCooler + '\'' +
                ", maxGpuWidth='" + maxGpuWidth + '\'' +
                '}';
    }
}

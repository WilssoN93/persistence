package com.Hardwar.Persistence.Entitys;

import javax.persistence.*;

@MappedSuperclass
public abstract class ComputerComponent {

    protected Long id;
    protected String name;
    protected int price;
    protected String url;
    protected boolean parsed;


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
    public int getPrice() {
        return price;
    }

    @Column(name = "url")
    public String getUrl() {
        return url;
    }
    @Column(name = "parsed")
    public boolean isParsed() {
        return parsed;
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

    public void setUrl(String url) {
        this.url = url;
    }

    public void setParsed(boolean parsed) {
        this.parsed = parsed;
    }
}

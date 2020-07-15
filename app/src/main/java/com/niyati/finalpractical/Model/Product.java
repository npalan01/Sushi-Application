package com.niyati.finalpractical.Model;

public class Product {
    private int id;
    private String name;
    private String desc;
    private double price;
    private String imageName;
    public Product(int id, String name, String desc, double price, String imageName) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.imageName = imageName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}

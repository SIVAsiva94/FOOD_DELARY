package com.example.fooddelary;

public class FavorModel {
    private  String name;
    private String type;
    private String cost;
    private String img;

    public FavorModel(String name, String type, String cost,String img) {
        this.name = name;
        this.type = type;
        this.img=img;
        this.cost = cost;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}

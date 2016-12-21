package com.naruku.fisher.homescreen;

/**
 * Created by Lincoln on 18/05/16.
 */
public class Fishes {
    private String name;
    private int quantity;
    private int thumbnail;

    public Fishes() {
    }

    public Fishes(String name, int quantity, int thumbnail) {
        this.name = name;
        this.quantity = quantity;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}

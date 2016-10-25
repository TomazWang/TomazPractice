package com.wishmobile.tomazpractice.wallet.data;

/**
 * Created by TomazWang on 2016/10/21.
 */

public class CostItem {


    long id;
    int cost;
    String name;

    // TODO: category


    public CostItem(long id) {
        this.id = id;
    }

    public CostItem(long id, int cost, String name) {
        this.id = id;
        this.cost = cost;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CostItem costItem = (CostItem) o;

        if (cost != costItem.cost) return false;
        return name.equals(costItem.name);

    }

    @Override
    public int hashCode() {
        int result = cost;
        result = 31 * result + name.hashCode();
        return result;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

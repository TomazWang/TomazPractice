package com.wishmobile.tomazpractice.wallet.data;


import android.support.annotation.IntDef;

import java.util.ArrayList;

public class Wallet {

    public static final int CASH = 378;
    public static final int CARD = 341;

    @IntDef({CASH, CARD})
    public @interface Type {

    }
    @Wallet.Type
    int type;

    long id;


    String title;
    int budget;
    private int balance;
    ArrayList<CostItem> costItems = new ArrayList<>();

    private boolean isBalanceCounted = false;

    public Wallet(long id, int type, String title, int budget) {
        this.type = type;
        this.id = id;
        this.title = title;
        this.budget = budget;
    }


    public int getBalance() {

        if (!isBalanceCounted) {

            int tempBalance = budget;

            for (CostItem costItem : costItems) {
                tempBalance -= costItem.getCost();
            }

            this.balance = tempBalance;
        }

        return balance;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(@Wallet.Type int type) {
        this.type = type;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public CostItem getCostItem(int position) {
        return getCostItems().get(position);
    }

    public ArrayList<CostItem> getCostItems() {
        return costItems;
    }


    public void addCostItem(CostItem costItem) {
        this.costItems.add(costItem);
        this.isBalanceCounted = false;
    }

    public void removeCostItem(CostItem removeItem) {
        this.costItems.remove(costItems);
        this.isBalanceCounted = false;
    }


}

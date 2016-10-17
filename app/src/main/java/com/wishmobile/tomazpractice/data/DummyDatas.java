package com.wishmobile.tomazpractice.data;

/**
 * Created by TomazWang on 2016/10/17.
 */

public class DummyDatas {

    String title;
    String name;
    int imageId;

    public DummyDatas(String title, String name, int imageId) {
        this.title = title;
        this.name = name;
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}

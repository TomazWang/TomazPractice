package com.wishmobile.tomazpractice.data;

/**
 * Created by TomazWang on 2016/10/17.
 */

public class DummyDatas {

    int id;
    String title;
    String name;
    int imageId;
    int value = 0;

    public DummyDatas(int id) {
        this(id, "title "+id, "name "+id);
    }

    public DummyDatas(int id, String title, String name){
        this.id = id;
        this.title = title;
        this.name = name;
        this.value = id;
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

    public int getId() {
        return id;
    }
}

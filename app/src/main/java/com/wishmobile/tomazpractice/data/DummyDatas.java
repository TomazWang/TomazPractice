package com.wishmobile.tomazpractice.data;

/**
 * Created by TomazWang on 2016/10/17.
 */

public class DummyDatas {

    int id;
    String title_prefix;
    String namePrefix;
    int imageId;
    int value = 0;

    public DummyDatas(int id) {
        this(id, "title ", "value ");
    }

    public DummyDatas(int id, String title, String name){
        this.id = id;
        this.title_prefix = title;
        this.namePrefix = name;
        this.value = id;
    }


    public String getTitle() {
        return title_prefix + id;
    }

    public void setTitlePrefix(String titlePrefix) {
        this.title_prefix = titlePrefix;
    }

    public String getName() {
        return namePrefix + value;
    }

    public void setNamePrefix(String namePrefix) {
        this.namePrefix = namePrefix;
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

    public void addValue() {
        this.value ++;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

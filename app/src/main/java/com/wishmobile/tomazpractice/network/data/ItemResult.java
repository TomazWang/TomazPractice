package com.wishmobile.tomazpractice.network.data;

import java.util.ArrayList;

/**
 * Created by TomazWang on 2016/10/26.
 */

public class ItemResult {

    public class Product {

        long id;
        String title;

        ImageData feature_image;
        String feature_image_color_code;

        int original_price;
        int sale_price;
        String price_unit;

        public long getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public ImageData getFeature_image() {
            return feature_image;
        }

        public String getFeature_image_color_code() {
            return feature_image_color_code;
        }

        public int getOriginal_price() {
            return original_price;
        }

        public int getSale_price() {
            return sale_price;
        }

        public String getPrice_unit() {
            return price_unit;
        }
    }


    public class ImageData {
        int width;
        int height;
        String url;

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        public String getUrl() {
            return url;
        }
    }

    public ArrayList<Product> product_info = new ArrayList<>();
    public int next;

    public ArrayList<Product> getProductList() {
        return product_info;
    }

    public Product getProduct(int position){
        if(position < product_info.size()) {
            return getProduct(position);
        }else{
            return  null;
        }
    }

    public int getNext() {
        return next;
    }
}

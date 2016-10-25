package com.wishmobile.tomazpractice.network.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TomazWang on 2016/10/25.
 */

public class FavLangQuestion {
    String question ="";
    String vote_date = "";
    List<LangChoice> choices;

    public FavLangQuestion(){
        this.choices = new ArrayList<>();
    }
}

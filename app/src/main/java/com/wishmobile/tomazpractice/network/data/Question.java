package com.wishmobile.tomazpractice.network.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TomazWang on 2016/10/25.
 */

public class Question {
    String question ="";
    String vote_date = "";
    List<Choice> choices;

    public Question(){
        this.choices = new ArrayList<>();
    }


    public String getQuestion() {
        return question;
    }

    public String getVote_date() {
        return vote_date;
    }

    public List<Choice> getChoices() {
        return choices;
    }
}

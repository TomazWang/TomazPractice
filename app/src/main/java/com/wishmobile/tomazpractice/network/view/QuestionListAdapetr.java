package com.wishmobile.tomazpractice.network.view;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.linkwish.widget.FormView;
import com.wishmobile.tomazpractice.R;
import com.wishmobile.tomazpractice.network.data.Choice;
import com.wishmobile.tomazpractice.network.data.Question;
import com.wishmobile.tomazpractice.view.recyclerview.DataListAdapter;
import com.wishmobile.tomazpractice.widget.formview.TextItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by TomazWang on 2016/10/26.
 */

public class QuestionListAdapetr extends DataListAdapter<Question, QuestionListAdapetr.ViewHolder> {

    private static final String TAG = QuestionListAdapetr.class.getSimpleName();

    public QuestionListAdapetr(ArrayList<Question> dataList) {
        super(dataList, R.layout.item_question_list);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(getSimpleItemView(parent));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Question q = getItem(position);

        holder.qTitle.setText(q.getQuestion());
        holder.qVoteDate.setText(q.getVote_date());

        FormView.Adapter adapter = new FormView.Adapter();

        for (Choice choice : q.getChoices()) {
            Log.d(TAG, "onBindViewHolder: add choice into fromview");
            adapter.add(new TextItem(holder.itemView.getContext(), choice.getChoice(), String.valueOf(choice.getVotes())));
        }

        holder.choiceList.setAdapter(adapter);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.block_title)
        View titleBlock;

        @BindView(R.id.txt_q_title)
        TextView qTitle;

        @BindView(R.id.txt_q_vote_date)
        TextView qVoteDate;


        @BindView(R.id.list_choice)
        FormView choiceList;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }
}

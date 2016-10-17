package com.wishmobile.tomazpractice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by TomazWang on 2016/10/17.
 */

public class MenuButtonListAdapter extends BaseAdapter{

    private final ArrayList<String> menuItems;

    public MenuButtonListAdapter(ArrayList<String> menuItems) {
        this.menuItems = menuItems;
    }

    @Override
    public int getCount() {
        return menuItems.size();
    }

    @Override
    public Object getItem(int position) {
        return menuItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if(convertView == null) {
            // view haven't be inflated
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.item_menu_button, parent, false);
            holder = new ViewHolder(convertView);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.getItemButton().setText((String)getItem(position));

        return convertView;
    }


    private class ViewHolder {

        TextView itemButton;

        public TextView getItemButton() {
            return itemButton;
        }


        public ViewHolder(View view) {
            itemButton = (TextView) view.findViewById(R.id.btn_item);
            view.setTag(this);
        }
    }



}

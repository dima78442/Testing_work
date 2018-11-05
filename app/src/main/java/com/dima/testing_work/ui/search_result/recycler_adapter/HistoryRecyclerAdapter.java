package com.dima.testing_work.ui.search_result.recycler_adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dima.testing_work.R;

import java.util.ArrayList;


public class HistoryRecyclerAdapter extends RecyclerView.Adapter<HistoryRecyclerAdapter.MyViewHolder>{

    private ArrayList<String> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        ImageView imageView;
        TextView text;


        public MyViewHolder(View itemView) {
            super(itemView);

            cv = itemView.findViewById(R.id.cv);
            imageView = itemView.findViewById(R.id.imageView_result);
            text = itemView.findViewById(R.id.text_result);

        }

    }

    public HistoryRecyclerAdapter(ArrayList<String> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.result_card, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
        CardView cv = holder.cv;
        ImageView imageView= holder.imageView;
        TextView text = holder.text;
        text.setText(dataSet.get(listPosition));
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

}


package com.dima.testing_work.ui.main.fragments.saved_fragment.recycler_adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.dima.testing_work.R;
import com.dima.testing_work.data.db.model.ItemSaved;
import com.dima.testing_work.ui.search_result.SearchResultPresenter;

import java.util.List;

public class HistoryRecyclerAdapter extends RecyclerView.Adapter<HistoryRecyclerAdapter.MyViewHolder>{

    private List<ItemSaved> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        ImageView imageView;
        TextView text;
        CheckBox checkBox;


        public MyViewHolder(View itemView) {
            super(itemView);

            cv = itemView.findViewById(R.id.cv_saved);
            imageView = itemView.findViewById(R.id.imageView_saved);
            text = itemView.findViewById(R.id.text_saved);
            checkBox = itemView.findViewById(R.id.checkBox_saved);

        }

    }

    public HistoryRecyclerAdapter(List<ItemSaved> dataSet) {
        this.dataSet = dataSet;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                                                                          int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.saved_card, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
        CardView cv = holder.cv;
        ImageView imageView= holder.imageView;
        TextView text = holder.text;
        final CheckBox checkBox = holder.checkBox;
        String str = dataSet.get(listPosition).getName();
        String [] parts = str.split(",|;", 2);
        text.setText(parts[0]);
        String url = dataSet.get(listPosition).getImg();
        //Log.d("Retro",dataSet.get(listPosition).getUrl());
        SearchResultPresenter.setImage(url,imageView);
        checkBox.setChecked(false);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkBox.isChecked()){
                    dataSet.get(holder.getAdapterPosition()).setChecked(true);
                }else {
                    dataSet.get(holder.getAdapterPosition()).setChecked(false);
                }
            }
        });

        //String string = ;
    }


    public void clear(){
        dataSet.removeAll(dataSet);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

}


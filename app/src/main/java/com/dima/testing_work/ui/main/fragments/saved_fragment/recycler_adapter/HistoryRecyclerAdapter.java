package com.dima.testing_work.ui.main.fragments.saved_fragment.recycler_adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.dima.testing_work.R;
import com.dima.testing_work.data.db.model.ItemSaved;
import com.dima.testing_work.ui.main.fragments.saved_fragment.FragmentSavedPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryRecyclerAdapter extends RecyclerView.Adapter<HistoryRecyclerAdapter.MyViewHolder>{

    private List<ItemSaved> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageView_saved)
        ImageView imageView;
        @BindView(R.id.text_saved)
        TextView text;
        @BindView(R.id.checkBox_saved)
        CheckBox checkBox;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
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

        ImageView imageView= holder.imageView;
        TextView text = holder.text;
        final CheckBox checkBox = holder.checkBox;

        text.setText(format_title(listPosition));

        String url = dataSet.get(listPosition).getImg();
        FragmentSavedPresenter.setImage(url,imageView);

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

    }

    private String format_title (final int listPosition){

        String str = dataSet.get(listPosition).getName();
        String [] parts = str.split(",|;", 2);

        return parts[0];
    }

    public void clear(){
        dataSet.removeAll(dataSet);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

}


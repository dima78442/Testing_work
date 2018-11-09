package com.dima.testing_work.ui.search_result.recycler_adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dima.testing_work.R;
import com.dima.testing_work.data.Network.model.model.search.Result;
import com.dima.testing_work.ui.search_result.SearchResultPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HistoryRecyclerAdapter extends RecyclerView.Adapter<HistoryRecyclerAdapter.MyViewHolder>{

    private List<Result> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageView_result)
        ImageView imageView;
        @BindView(R.id.text_result)
        TextView text;


        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

    }

    public HistoryRecyclerAdapter(List<Result> dataSet) {
        this.dataSet = dataSet;
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
        ImageView imageView= holder.imageView;
        TextView text = holder.text;

        text.setText(title_format(listPosition));

        String url = dataSet.get(listPosition).getImages().get(0).getUrlFullxfull();
        SearchResultPresenter.setImage(url,imageView);
    }

    private String title_format(final int listPosition){
        String str = dataSet.get(listPosition).getTitle();
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


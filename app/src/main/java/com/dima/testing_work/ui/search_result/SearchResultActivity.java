package com.dima.testing_work.ui.search_result;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dima.testing_work.R;
import com.dima.testing_work.ui.search_result.recycler_adapter.HistoryRecyclerAdapter;
import com.dima.testing_work.ui.search_result.recycler_adapter.RecyclerOnClik.RecyclerItemClickListener;

import java.util.ArrayList;

public class SearchResultActivity extends AppCompatActivity implements SearchResultMvpView{

    RecyclerView recyclerView;
    HistoryRecyclerAdapter historyRecyclerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serch_result);
        recyclerView = findViewById(R.id.result_recycler);

        LinearLayoutManager llm = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(llm);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        historyRecyclerAdapter = new HistoryRecyclerAdapter(arrayList);
        recyclerView.setAdapter(historyRecyclerAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView,new RecyclerItemClickListener.OnItemClickListener(){
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
    }

    @Override
    public void updateView() {
        historyRecyclerAdapter.notifyDataSetChanged();
    }
}

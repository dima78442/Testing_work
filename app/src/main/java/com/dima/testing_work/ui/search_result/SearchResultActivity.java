package com.dima.testing_work.ui.search_result;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.dima.testing_work.R;
import com.dima.testing_work.data.DataManager;
import com.dima.testing_work.data.Network.model.EtsyNetwork;
import com.dima.testing_work.ui.search_result.recycler_adapter.HistoryRecyclerAdapter;
import com.dima.testing_work.ui.search_result.recycler_adapter.RecyclerOnClik.RecyclerItemClickListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchResultActivity extends AppCompatActivity implements SearchResultMvpView{

    @BindView(R.id.result_recycler)
    RecyclerView recyclerView;

    HistoryRecyclerAdapter historyRecyclerAdapter;
    SearchResultPresenter presenter;
    LinearLayoutManager llm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serch_result);

        ButterKnife.bind(this);
        ProgressBar progressBar = findViewById(R.id.progressBar2);
        progressBar.setVisibility(ProgressBar.VISIBLE);
        presenter = new SearchResultPresenter(new DataManager(null,null,new EtsyNetwork()));
        presenter.onAttach(this);
        presenter.getSearchResults("images", EtsyNetwork.API_KEY,"paper_goods","terminator",5,0);

        llm = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(llm);
        //recyclerView.setVisibility(RecyclerView.INVISIBLE);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }

}

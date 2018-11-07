package com.dima.testing_work.ui.search_result;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dima.testing_work.R;
import com.dima.testing_work.data.DataManager;
import com.dima.testing_work.data.Network.model.EtsyNetwork;
import com.dima.testing_work.data.Network.model.model.search.ResponseSearch;
import com.dima.testing_work.data.Network.model.model.search.Result;
import com.dima.testing_work.ui.detail.DetailActivity;
import com.dima.testing_work.ui.search_result.recycler_adapter.HistoryRecyclerAdapter;
import com.dima.testing_work.ui.search_result.recycler_adapter.recyclerListeners.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchResultActivity extends AppCompatActivity implements SearchResultMvpView{

    @BindView(R.id.result_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.progressBar2)
    ProgressBar progressBar;
    @BindView(R.id.progressBar3)
    ProgressBar progressBar2;
    @BindView(R.id.swipe_search)
    SwipeRefreshLayout swipeRefreshLayout;

    HistoryRecyclerAdapter historyRecyclerAdapter;
    SearchResultPresenter presenter;
    LinearLayoutManager llm;
    List<Result> results = new ArrayList<>();
    ResponseSearch responseSearch;

    private final int PAGE_SIZE = 5;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serch_result);

        ButterKnife.bind(this);

        recyclerView.setVisibility(RecyclerView.INVISIBLE);
        progressBar.setVisibility(ProgressBar.VISIBLE);

        presenter = new SearchResultPresenter(new DataManager(null,null,new EtsyNetwork()));
        presenter.onAttach(this);
        presenter.getSearchResults("Images", EtsyNetwork.API_KEY,"paper_goods","terminator",PAGE_SIZE,counter);
        counter = counter + 5;

        //presenter.getSearchResults("images", EtsyNetwork.API_KEY,"paper_goods","terminator",5,0);

        llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        historyRecyclerAdapter = new HistoryRecyclerAdapter(results);
        recyclerView.setAdapter(historyRecyclerAdapter);
        recyclerView.addOnScrollListener(recyclerViewOnScrollListener);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView,new RecyclerItemClickListener.OnItemClickListener(){
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(SearchResultActivity.this,"t",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(SearchResultActivity.this, DetailActivity.class);
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                historyRecyclerAdapter.clear();
                historyRecyclerAdapter.notifyDataSetChanged();
                presenter.setLastPage(false);
                counter = 0;
                presenter.getSearchResults("Images", EtsyNetwork.API_KEY,"paper_goods","terminator",PAGE_SIZE,counter);
                counter = 5;
            }
        });

    }

    private RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int visibleItemCount = llm.getChildCount();
            int totalItemCount = llm.getItemCount();
            int firstVisibleItemPosition = llm.findFirstVisibleItemPosition();

            if (!presenter.isLoading() && !presenter.isLastPage() && !swipeRefreshLayout.isRefreshing()) {
                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= PAGE_SIZE) {
                    presenter.getSearchResults("Images", EtsyNetwork.API_KEY,"paper_goods","terminator",PAGE_SIZE,counter);
                    counter = counter + 5;
                    progressBar2.setVisibility(ProgressBar.VISIBLE);
                }
            }
        }
    };

    @Override
    public void results_process(){
        responseSearch = presenter.getResponseSearch();
        //results = responseSearch.getResults();
        results.addAll(responseSearch.getResults());


    }

    @Override
    public void updateView() {
        recyclerView.setVisibility(RecyclerView.VISIBLE);
        swipeRefreshLayout.setRefreshing(false);
        progressBar.setVisibility(ProgressBar.INVISIBLE);
        progressBar2.setVisibility(ProgressBar.INVISIBLE);
        historyRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //historyRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }

}

package com.dima.testing_work.ui.main.fragments.saved_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dima.testing_work.R;
import com.dima.testing_work.data.DataManager;
import com.dima.testing_work.data.db.AppDbHelper;
import com.dima.testing_work.data.db.DbOpenHelper;
import com.dima.testing_work.data.db.model.ItemSaved;
import com.dima.testing_work.ui.main.fragments.saved_fragment.detail_saved_activity.DetailSavedActivity;
import com.dima.testing_work.ui.main.fragments.saved_fragment.recycler_adapter.HistoryRecyclerAdapter;
import com.dima.testing_work.ui.search_result.recycler_adapter.recyclerListeners.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Saved extends Fragment implements SavedMvpView {

    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    private HistoryRecyclerAdapter historyRecyclerAdapter;
    private LinearLayoutManager llm;
    private FragmentSavedPresenter presenter;
    private List<ItemSaved> items = new ArrayList<>();

    public Saved() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.history,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        initPresenter();
        llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);
        historyRecyclerAdapter = new HistoryRecyclerAdapter(items);
        recyclerView.setAdapter(historyRecyclerAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), recyclerView,new RecyclerItemClickListener.OnItemClickListener(){
            @Override
            public void onItemClick(View view, final int position) {
                TextView textView = view.findViewById(R.id.text_saved);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ItemSaved itemSaved = items.get(position);
                        Intent intent = new Intent(getActivity(), DetailSavedActivity.class);
                        intent.putExtra("description",itemSaved.getDescription());
                        intent.putExtra("title",itemSaved.getName());
                        intent.putExtra("price",itemSaved.getPrice());
                        intent.putExtra("url",itemSaved.getImg());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
    }

    private void initPresenter(){
        presenter = new FragmentSavedPresenter(new DataManager(getContext(),new AppDbHelper(new DbOpenHelper(getActivity().getApplicationContext())),null));
        presenter.onAttach(this);
        presenter.getAllItems();

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        deleteChecked();
        //updateView();
        return super.onOptionsItemSelected(item);
    }

    private void deleteChecked(){
        for (int i = items.size()-1;i!=-1;i--){
            if (items.get(i).isChecked()) {
                presenter.deleteItem(items.get(i));
                items.remove(i);
            }
        }
        historyRecyclerAdapter.notifyDataSetChanged();
    }
    @Override
    public void onResume() {
        super.onResume();
        items.clear();
        presenter.getAllItems();
        historyRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }

    @Override
    public void setData(List<ItemSaved> items) {
        this.items.addAll(items);
    }

    @Override
    public void updateView() {
        historyRecyclerAdapter.notifyDataSetChanged();
    }

}
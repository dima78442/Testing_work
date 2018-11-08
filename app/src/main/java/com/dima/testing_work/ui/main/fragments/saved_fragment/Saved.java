package com.dima.testing_work.ui.main.fragments.saved_fragment;

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

import com.dima.testing_work.R;
import com.dima.testing_work.data.DataManager;
import com.dima.testing_work.data.db.AppDbHelper;
import com.dima.testing_work.data.db.DbOpenHelper;
import com.dima.testing_work.data.db.model.ItemSaved;

import com.dima.testing_work.ui.main.fragments.saved_fragment.recycler_adapter.HistoryRecyclerAdapter;

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

        /*recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                view.findViewById(R.id.cv).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent launchIntent = getContext().getPackageManager().getLaunchIntentForPackage("com.dima.testing_big_dig_b");
                        if (launchIntent != null) {

                            CardView cardView = (CardView)view.findViewById(R.id.cv);
                            TextView url = view.findViewById(R.id.url);
                            TextView id = view.findViewById(R.id.id_reference);
                            TextView status = view.findViewById(R.id.status_reference);

                            Bundle bundle = new Bundle();
                            bundle.putString("id",id.getText().toString());
                            bundle.putString("from","history");
                            bundle.putString("status",status.getText().toString());
                            bundle.putString("url",url.getText().toString());
                            bundle.putString("time",timeGetter());
                            launchIntent.putExtra("Reference",bundle);

                            startActivity(launchIntent);//null pointer check in case package name was not found
                        }
                    }
                });
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));*/
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
/*
        if (item.getTitle().equals("sort1")) {

            references.sort(ComparatorStatus.sort_status);
            historyRecyclerAdapter.notifyDataSetChanged();

        }else if (item.getTitle().equals("sort2")) {

            references.sort(ComparatorTime.sort_time);
            historyRecyclerAdapter.notifyDataSetChanged();
        }*/
        return super.onOptionsItemSelected(item);
    }

    /*@Override
    public void onResume() {
        super.onResume();
        historyRecyclerAdapter.notifyDataSetChanged();
    }*/

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
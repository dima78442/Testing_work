
package com.dima.testing_work.ui.main.fragments.loading_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.dima.testing_work.MyApplication;
import com.dima.testing_work.R;
import com.dima.testing_work.data.Network.model.EtsyNetwork;
import com.dima.testing_work.injection.component.ActivityComponent;
import com.dima.testing_work.injection.component.DaggerActivityComponent;
import com.dima.testing_work.injection.module.ActivityModule;
import com.dima.testing_work.ui.main.fragments.search_fragment.Search;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Loading extends Fragment implements LoadingMvpView{

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Inject
    FragmentLoadingPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.loading,container,false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this,view);
        activityComponent().inject(this);

        presenterInit();

    }

    @Override
    public void toSearch(List<String> categories) {
        progressBar.setVisibility(ProgressBar.INVISIBLE);
        toSearchFragment(categories);

    }

    private void toSearchFragment(List<String> categories){
        Fragment fragment = new Search();

        Bundle bundle = new Bundle();
        bundle.putStringArrayList("categories",new ArrayList<String>(categories));

        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.loading, fragment);
        fragmentTransaction.commit();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }

    private void presenterInit(){
        presenter.onAttach(this);
        presenter.getCategories(EtsyNetwork.API_KEY);
    }

    public ActivityComponent activityComponent() {

        return DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(getActivity()))
                .applicationComponent(((MyApplication)getActivity().getApplication()).getComponent())
                .build();
    }

}

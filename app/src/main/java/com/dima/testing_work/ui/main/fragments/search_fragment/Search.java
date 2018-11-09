
package com.dima.testing_work.ui.main.fragments.search_fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.dima.testing_work.R;
import com.dima.testing_work.ui.search_result.SearchResultActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Search extends Fragment {

    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.editText)
    EditText editText;
    /*@BindView(R.id.button)
    Button button;*/
    ArrayList<String> categories;

    ArrayAdapter<String> adapter;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public Search() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        Bundle bundle = getArguments();
        categories = bundle.getStringArrayList("categories");
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        // выделяем элемент
        //final EditText editText = (EditText)view.findViewById(R.id.editText);

        //Button button = (Button)view.findViewById(R.id.button);

       /* button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent launchIntent = getContext().getPackageManager().getLaunchIntentForPackage("com.dima.testing_big_dig_b");

                if (launchIntent != null) {

                    Bundle bundle = new Bundle();
                    bundle.putString("from","test");
                    bundle.putString("url",editText.getText().toString());
                    bundle.putString("time",timeGetter());
                    launchIntent.putExtra("Reference",bundle);

                    startActivity(launchIntent);//null pointer check in case package name was not found
                }
            }
        });*/
    }

   /* public String timeGetter(){
        String currentDateandTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        Log.d("tm","" + currentDateandTime);
        return currentDateandTime;
    }*/

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @OnClick(R.id.button)
    public void submit(View view) {
        /*ApiTest apiTest = new ApiTest();
        apiTest.setup();
        apiTest.action2();*/
//        for (int i = 0; i < categories.size(); i++) {
//           Log.d("Retro",categories.get(i));
//        }
       /* Fragment fragment = new Loading();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.search, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();*/
        startSearchResultActivity();
    }

    public void startSearchResultActivity(){
        Intent intent = new Intent(getActivity(),SearchResultActivity.class);
        intent.putExtra("text_search",editText.getText().toString());
        intent.putExtra("category_search",spinner.getSelectedItem().toString());
        startActivity(intent);
    }
}

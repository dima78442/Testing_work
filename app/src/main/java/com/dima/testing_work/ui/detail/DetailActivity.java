package com.dima.testing_work.ui.detail;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dima.testing_work.R;
import com.dima.testing_work.data.DataManager;
import com.dima.testing_work.data.db.AppDbHelper;
import com.dima.testing_work.data.db.DbOpenHelper;
import com.dima.testing_work.data.db.model.ItemSaved;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailActivity extends AppCompatActivity implements DetailMvpView {

    @BindView(R.id.image_detail)
    ImageView image;
    @BindView(R.id.title_detail)
    TextView title;
    @BindView(R.id.price_detail)
    TextView price;
    @BindView(R.id.description_detail)
    TextView description;
    private DetailActivityPresenter presenter;
    private ItemSaved itemSaved;
    private String title_intent;
    private String price_intent;
    private String description_intent;
    private String url_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        initPresenter();
        intentParser();
        imageSetter();
        textViewSetValueAndStyle();


    }

    private void initPresenter(){
        presenter = new DetailActivityPresenter(new DataManager(this,new AppDbHelper(new DbOpenHelper(getApplicationContext())),null));
        presenter.onAttach(this);

    }
    private void intentParser(){
        Intent intent = getIntent();
        title_intent = intent.getStringExtra("title");
        price_intent = intent.getStringExtra("price");
        description_intent = intent.getStringExtra("description");
        url_intent = intent.getStringExtra("url");
    }
    private void textViewSetValueAndStyle(){
        title.setText(title_intent);
        price.setText(price_intent);
        description.setText(description_intent);

        title.setTypeface(null, Typeface.BOLD);
        price.setTypeface(null, Typeface.BOLD);
        description.setTypeface(null, Typeface.BOLD);
    }

    private void imageSetter(){
        presenter.setImage(url_intent,image);
    }

    private void itemCreator(){
        itemSaved = presenter.itemCreator(title_intent,description_intent,price_intent,url_intent);
    }

    @Override
    public void makeToast(String text) {
        Toast.makeText(this,text,Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.floatingActionButton2)
    public void click(View view){
        itemCreator();
        presenter.insetItem(itemSaved);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }
}
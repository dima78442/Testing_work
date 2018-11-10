package com.dima.testing_work.ui.main.fragments.saved_fragment.detail_saved_activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dima.testing_work.R;
import com.dima.testing_work.ui.detail.DetailMvpView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailSavedActivity extends AppCompatActivity implements DetailMvpView {

    @BindView(R.id.image_saved)
    ImageView image;
    @BindView(R.id.title_saved)
    TextView title;
    @BindView(R.id.price_saved)
    TextView price;
    @BindView(R.id.description_saved)
    TextView description;

    private String title_intent;
    private String price_intent;
    private String description_intent;
    private String url_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deatil_saved);

        ButterKnife.bind(this);

        noTitle();
        intentParser();
        imageSetter();
        textViewSetValueAndStyle();
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
        DetailSavedActivityPresenter.setImage(url_intent,image);
    }

    @Override
    public void makeToast(String text) {
        Toast.makeText(this,text,Toast.LENGTH_LONG).show();
    }

    private void noTitle(){
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
}
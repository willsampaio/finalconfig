package com.example.finalconfig.finalconfig;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class ItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        ImageView im = (ImageView) findViewById(R.id.imageViewWifi);
        im.setImageResource(R.drawable.switch_off);

    }
}

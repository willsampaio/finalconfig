package com.example.finalconfig.finalconfig;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SobreActivity extends AppCompatActivity {

    ImageView imageViewIcon;
    TextView textViewSite, textViewTel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        imageViewIcon = (ImageView) findViewById(R.id.imageViewIcon);
        textViewSite = (TextView) findViewById(R.id.textViewSite);
        textViewTel = (TextView) findViewById(R.id.textViewNumero);

        textViewSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirSite();
            }
        });

        textViewTel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                discarNumero();
            }
        });
    }

    private void abrirSite(){
        Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + getString(R.string.info_unit_site)));
        startActivity(it);
    }

    private void discarNumero(){
        Intent it = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + getString(R.string.info_unit_tel)));
        startActivity(it);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

package com.example.finalconfig.finalconfig;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class ItemActivity extends AppCompatActivity {

    ImageView conf_sinc, conf_wifi, conf_dados, conf_bt, conf_gps, conf_som;
    TextView dia_dom, dia_seg, dia_ter, dia_qua, dia_qui, dia_sex, dia_sab, hora_inicio, hora_fim;
    SeekBar duracao;
    Button btSalvar;
    Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        configuracao();
        setItem();

        if(item.getId() != -1){
            showItem();
        }

    }

    private void configuracao(){
        conf_sinc= (ImageView) findViewById(R.id.imageViewSinc);
        conf_wifi= (ImageView) findViewById(R.id.imageViewWifi);
        conf_dados= (ImageView) findViewById(R.id.imageViewDados);
        conf_bt= (ImageView) findViewById(R.id.imageViewBt);
        conf_gps= (ImageView) findViewById(R.id.imageViewGPS);
        conf_som= (ImageView) findViewById(R.id.imageViewSom);

        dia_dom	= (TextView) findViewById(R.id.textViewDom);
        dia_seg	= (TextView) findViewById(R.id.textViewSeg);
        dia_ter	= (TextView) findViewById(R.id.textViewTer);
        dia_qua	= (TextView) findViewById(R.id.textViewQua);
        dia_qui	= (TextView) findViewById(R.id.textViewQui);
        dia_sex	= (TextView) findViewById(R.id.textViewSex);
        dia_sab	= (TextView) findViewById(R.id.textViewSab);
        hora_inicio	= (TextView) findViewById(R.id.textViewInicio);
        hora_fim = (TextView) findViewById(R.id.textViewFim);

        duracao = (SeekBar) findViewById(R.id.seekBar);

        btSalvar = (Button) findViewById(R.id.btSalvar);

        item = new Item(-1);

        setCliks();
    }

    private void setItem(){
        int id = getIntent().getIntExtra(getString(R.string.par_id), -1);

        if(id != -1) {
            ItemADO iado = new ItemADO(this);
            item = iado.buscarItemId(new Item(id));
        }
    }

    private void showItem(){
        hora_inicio.setText(item.getHora_inicio());
        hora_fim.setText(item.getHora_fim());

        int colorId = R.color.colorAccent;

        if(item.isDia_dom()){
            dia_dom.setTextColor(getResources().getColor(colorId));
        }

        if(item.isDia_seg()){
            dia_seg.setTextColor(getResources().getColor(colorId));
        }

        if(item.isDia_ter()){
            dia_ter.setTextColor(getResources().getColor(colorId));
        }

        if(item.isDia_qua()){
            dia_qua.setTextColor(getResources().getColor(colorId));
        }

        if(item.isDia_qui()){
            dia_qui.setTextColor(getResources().getColor(colorId));
        }

        if(item.isDia_sex()){
            dia_sex.setTextColor(getResources().getColor(colorId));
        }

        if(item.isDia_sab()){
            dia_sab.setTextColor(getResources().getColor(colorId));
        }

        int onId = R.drawable.switch_on;
        int offId = R.drawable.switch_off;

        if(item.isConf_sinc()){
            conf_sinc.setImageResource(onId);
        }else {
            conf_sinc.setImageResource(offId);
        }

        if(item.isConf_wifi()){
            conf_wifi.setImageResource(onId);
        }else {
            conf_wifi.setImageResource(offId);
        }

        if(item.isConf_dados()){
            conf_dados.setImageResource(onId);
        }else {
            conf_dados.setImageResource(offId);
        }

        if(item.isConf_bt()){
            conf_bt.setImageResource(onId);
        }else {
            conf_bt.setImageResource(offId);
        }

        if(item.isConf_gps()){
            conf_gps.setImageResource(onId);
        }else {
            conf_gps.setImageResource(offId);
        }

        if(item.isConf_som()){
            conf_som.setImageResource(onId);
        }else {
            conf_som.setImageResource(offId);
        }

    }

    private void setCliks(){
        conf_sinc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(item.isConf_sinc()){
                    item.setConf_sinc(false);
                    Toast.makeText(ItemActivity.this, "Desativar Sinc", Toast.LENGTH_SHORT).show();
                }else {
                    item.setConf_sinc(true);
                    Toast.makeText(ItemActivity.this, "Ativar Sinc", Toast.LENGTH_SHORT).show();
                }
            }
        });

        conf_wifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(item.isConf_wifi()){
                    item.setConf_wifi(false);
                    Toast.makeText(ItemActivity.this, "Desativar Wi-Fi", Toast.LENGTH_SHORT).show();
                }else {
                    item.setConf_wifi(true);
                    Toast.makeText(ItemActivity.this, "Ativar Wi-Fi", Toast.LENGTH_SHORT).show();
                }
            }
        });

        conf_dados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(item.isConf_dados()){
                    item.setConf_dados(false);
                    Toast.makeText(ItemActivity.this, "Desativar Dados", Toast.LENGTH_SHORT).show();
                }else {
                    item.setConf_dados(true);
                    Toast.makeText(ItemActivity.this, "Ativar Dados", Toast.LENGTH_SHORT).show();
                }
            }
        });

        conf_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(item.isConf_bt()){
                    item.setConf_bt(false);
                    Toast.makeText(ItemActivity.this, "Desativar Bluetooth", Toast.LENGTH_SHORT).show();
                }else {
                    item.setConf_bt(true);
                    Toast.makeText(ItemActivity.this, "Ativar Bluetooth", Toast.LENGTH_SHORT).show();
                }
            }
        });

        conf_gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(item.isConf_gps()){
                    item.setConf_gps(false);
                    Toast.makeText(ItemActivity.this, "Desativar GPS", Toast.LENGTH_SHORT).show();
                }else {
                    item.setConf_gps(true);
                    Toast.makeText(ItemActivity.this, "Ativar GPS", Toast.LENGTH_SHORT).show();
                }
            }
        });

        conf_som.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(item.isConf_som()){
                    item.setConf_som(false);
                    Toast.makeText(ItemActivity.this, "Desativar Som", Toast.LENGTH_SHORT).show();
                }else {
                    item.setConf_som(true);
                    Toast.makeText(ItemActivity.this, "Ativar Som", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ItemADO iado = new ItemADO(getApplicationContext());

                if(item.getId() == -1) {
                    iado.inserirItem(item);
                }else{
                    iado.atualizarItem(item);
                }

                finish();
            }
        });
    }




}

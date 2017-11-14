package com.example.finalconfig.finalconfig;

import android.annotation.TargetApi;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class ItemActivity extends AppCompatActivity {

    ImageView conf_sinc, conf_wifi, conf_dados, conf_bt, conf_gps, conf_som;
    TextView dia_dom, dia_seg, dia_ter, dia_qua, dia_qui, dia_sex, dia_sab, hora_inicio, hora_fim;
    SeekBar duracao;
    Button btSalvar;
    Item item;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        configuracao();
        setItem();
        showItem();

//        if(item.getId() != -1){
//            showItem();
//        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_apagar || id == R.id.action_apagar_ic) {
            apagar();
            finish();
            return true;
        }

        if (id == R.id.action_cancelar) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void configuracao(){
        conf_sinc= (ImageView) findViewById(R.id.imageViewSinc2);
        conf_wifi= (ImageView) findViewById(R.id.imageViewWifi2);
        conf_dados= (ImageView) findViewById(R.id.imageViewDados2);
        conf_bt= (ImageView) findViewById(R.id.imageViewBt2);
        conf_gps= (ImageView) findViewById(R.id.imageViewGPS2);
        conf_som= (ImageView) findViewById(R.id.imageViewSom2);

        dia_dom	= (TextView) findViewById(R.id.textViewDom2);
        dia_seg	= (TextView) findViewById(R.id.textViewSeg2);
        dia_ter	= (TextView) findViewById(R.id.textViewTer2);
        dia_qua	= (TextView) findViewById(R.id.textViewQua2);
        dia_qui	= (TextView) findViewById(R.id.textViewQui2);
        dia_sex	= (TextView) findViewById(R.id.textViewSex2);
        dia_sab	= (TextView) findViewById(R.id.textViewSab2);
        hora_inicio	= (TextView) findViewById(R.id.textViewInicio2);
        hora_fim = (TextView) findViewById(R.id.textViewFim2);

        duracao = (SeekBar) findViewById(R.id.seekBar2);

//        btSalvar = (Button) findViewById(R.id.btSalvar);

        String hr = getTimeSystem();
        String[] hm = hr.split(":");
        int h = Math.abs(Integer.parseInt(hm[0]) -3);
        int m = Integer.parseInt(hm[1]);

        item = new Item(-1);
        item.setHora_inicio(h + ":" + m);
        item.setHora_fim(h + ":" + m);

        setCliks();
    }

    private void setItem(){
        int id = getIntent().getIntExtra(getString(R.string.par_id), -1);

        if(id != -1) {
            ItemADO iado = new ItemADO(this);
            item = iado.buscarItemId(new Item(id));
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void showItem(){
        String hr0 = item.getHora_inicio();
        String[] hm0 = hr0.split(":");
        int h0 = Integer.parseInt(hm0[0]);

        String hr1 = item.getHora_fim();
        String[] hm1 = hr1.split(":");
        int h1 = Integer.parseInt(hm1[0]);

        String hr = getTimeSystem();
        String[] hm = hr.split(":");
        int h = Integer.parseInt(hm[0]) -3;

        int max = h1/h0;
        duracao.setMax(max);

        if(h > h0) {
            duracao.setProgress(h - h0);
        }

        hora_inicio.setText(hr0);
        hora_fim.setText(hr1);

        int colorId = R.color.colorAccent;
        int colorId0 = R.color.colorText;

//        if(item.getId() != -1){
//            hora_inicio.setTextColor(getResources().getColor(colorId));
//            hora_fim.setTextColor(getResources().getColor(colorId));
//        }else {
//            hora_inicio.setTextColor(getResources().getColor(colorId0));
//            hora_fim.setTextColor(getResources().getColor(colorId0));
//        }

        if(item.isDia_dom()){
            dia_dom.setTextColor(getResources().getColor(colorId));
        }else {
            dia_dom.setTextColor(getResources().getColor(colorId0));
        }

        if(item.isDia_seg()){
            dia_seg.setTextColor(getResources().getColor(colorId));
        }else {
            dia_seg.setTextColor(getResources().getColor(colorId0));
        }

        if(item.isDia_ter()){
            dia_ter.setTextColor(getResources().getColor(colorId));
        }else {
            dia_ter.setTextColor(getResources().getColor(colorId0));
        }

        if(item.isDia_qua()){
            dia_qua.setTextColor(getResources().getColor(colorId));
        }else {
            dia_qua.setTextColor(getResources().getColor(colorId0));
        }

        if(item.isDia_qui()){
            dia_qui.setTextColor(getResources().getColor(colorId));
        }else {
            dia_qui.setTextColor(getResources().getColor(colorId0));
        }

        if(item.isDia_sex()){
            dia_sex.setTextColor(getResources().getColor(colorId));
        }else {
            dia_sex.setTextColor(getResources().getColor(colorId0));
        }

        if(item.isDia_sab()){
            dia_sab.setTextColor(getResources().getColor(colorId));
        }else {
            dia_sab.setTextColor(getResources().getColor(colorId0));
        }

        if(item.isConf_sinc()){
            conf_sinc.setImageResource(R.drawable.sinc1);
        }else {
            conf_sinc.setImageResource(R.drawable.sinc0);
        }

        if(item.isConf_wifi()){
            conf_wifi.setImageResource(R.drawable.wifi1);
        }else {
            conf_wifi.setImageResource(R.drawable.wifi0);
        }

        if(item.isConf_dados()){
            conf_dados.setImageResource(R.drawable.dados1);
        }else {
            conf_dados.setImageResource(R.drawable.dados0);
        }

        if(item.isConf_bt()){
            conf_bt.setImageResource(R.drawable.bt1);
        }else {
            conf_bt.setImageResource(R.drawable.bt0);
        }

        if(item.isConf_gps()){
            conf_gps.setImageResource(R.drawable.gps1);
        }else {
            conf_gps.setImageResource(R.drawable.gps0);
        }

        if(item.isConf_som()){
            conf_som.setImageResource(R.drawable.audio1);
        }else {
            conf_som.setImageResource(R.drawable.audio0);
        }

    }

    private void setCliks(){

        hora_inicio.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                abrirRelogio('i');
                showItem();
            }
        });

        hora_fim.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                abrirRelogio('f');
                showItem();
            }
        });

        dia_dom.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                if(item.isDia_dom()) {
                    item.setDia_dom(false);
                }else {
                    item.setDia_dom(true);
                }
                showItem();
            }
        });

        dia_seg.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                if(item.isDia_seg()) {
                    item.setDia_seg(false);
                }else {
                    item.setDia_seg(true);
                }
                showItem();
            }
        });

        dia_ter.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                if(item.isDia_ter()) {
                    item.setDia_ter(false);
                }else {
                    item.setDia_ter(true);
                }
                showItem();
            }
        });

        dia_qua.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                if(item.isDia_qua()) {
                    item.setDia_qua(false);
                }else {
                    item.setDia_qua(true);
                }
                showItem();
            }
        });

        dia_qui.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                if(item.isDia_qui()) {
                    item.setDia_qui(false);
                }else {
                    item.setDia_qui(true);
                }
                showItem();
            }
        });

        dia_sex.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                if(item.isDia_sex()){
                    item.setDia_sex(false);
                }else {
                    item.setDia_sex(true);
                }
                showItem();
            }
        });

        dia_sab.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                if(item.isDia_sab()) {
                    item.setDia_sab(false);
                }else {
                    item.setDia_sab(true);
                }
                showItem();
            }
        });

        conf_sinc.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                if(item.isConf_sinc()){
                    item.setConf_sinc(false);
                }else {
                    item.setConf_sinc(true);
                }
                showItem();
            }
        });

        conf_wifi.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                if(item.isConf_wifi()){
                    item.setConf_wifi(false);
                }else {
                    item.setConf_wifi(true);
                }
                showItem();
            }
        });

        conf_dados.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                if(item.isConf_dados()){
                    item.setConf_dados(false);
                }else {
                    item.setConf_dados(true);
                }
                showItem();
            }
        });

        conf_bt.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                if(item.isConf_bt()){
                    item.setConf_bt(false);
                }else {
                    item.setConf_bt(true);
                }

                showItem();
            }
        });

        conf_gps.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                if(item.isConf_gps()){
                    item.setConf_gps(false);
                }else {
                    item.setConf_gps(true);
                }
                showItem();
            }
        });

        conf_som.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                if(item.isConf_som()){
                    item.setConf_som(false);
                }else {
                    item.setConf_som(true);
                }
                showItem();
            }
        });

//        btSalvar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                salvar();
//                finish();
//            }
//        });

    }

    private void salvar(){
        ItemADO iado = new ItemADO(getApplicationContext());

        if(item.getId() == -1) {
            iado.inserirItem(item);
        }else{
            iado.atualizarItem(item);
        }

    }

    private void apagar(){
        ItemADO iado = new ItemADO(getApplicationContext());
        if(item != null && item.id != -1) {
            iado.deletarItem(item);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void abrirRelogio(final char i_f){
        LayoutInflater li = getLayoutInflater();
        View view = li.inflate(R.layout.time_layout, null);

        final TimePicker timePicker = view.findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);

        if(i_f == 'i') {
            String hr = item.getHora_inicio();
            String[] hm = hr.split(":");
            int h = Integer.parseInt(hm[0]);
            int m = Integer.parseInt(hm[1]);
            timePicker.setHour(h);
            timePicker.setMinute(m);
        }else{
            String hr = item.getHora_fim();
            String[] hm = hr.split(":");
            int h = Math.abs(Integer.parseInt(hm[0]));
            int m = Integer.parseInt(hm[1]);
            timePicker.setHour(h);
            timePicker.setMinute(m);
        }

        Button btOk = view.findViewById(R.id.buttonSalvarHora);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        builder.setCancelable(false);
        final AlertDialog alerta = builder.create();
        alerta.show();

        btOk.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            public void onClick(View arg0) {
                String hr = String.format("%02d:%02d", timePicker.getHour(), timePicker.getMinute());

                switch (i_f) {
                    case 'i':
                        item.setHora_inicio(hr);
                        break;
                    case 'f':
                        item.setHora_fim(hr);
                        break;
                    default:
                        break;
                }
                showItem();
                alerta.dismiss();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String getTimeSystem(){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date hora = Calendar.getInstance().getTime(); // Ou qualquer outra forma que tem
        return sdf.format(hora);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        salvar();
    }
}

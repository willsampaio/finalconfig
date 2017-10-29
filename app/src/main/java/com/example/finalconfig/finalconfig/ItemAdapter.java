package com.example.finalconfig.finalconfig;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by willer_sampaio on 25/09/2017.
 */
public class ItemAdapter extends BaseAdapter {

    private List<Item> itemList;
    private Activity activity;
    private LayoutInflater layoutInflater;
    private ViewHolder viewHolder;


    public ItemAdapter(List<Item> itemList, Activity activity) {
        this.itemList = itemList;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Item getItem(int i) {
        return itemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return itemList.get(i).getId();
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if(view == null){
            layoutInflater = LayoutInflater.from(activity);
            view = layoutInflater.inflate(R.layout.item_layout, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }
        else
            viewHolder = (ViewHolder) view.getTag();

        Item item = getItem(i);
        viewHolder.setValues(item);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Item item = getItem(i);
                abrirPorId(item.getId());
            }
        });


//        viewHolder.conf_wifi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(activity, "aee", Toast.LENGTH_LONG).show();
//            }
//        });


        return view;
    }

    private void abrirPorId(int id){
        Bundle bundle = new Bundle();
        bundle.putInt(activity.getString(R.string.par_id), id);
        Intent intent = new Intent(activity, ItemActivity.class);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }


    private class ViewHolder {
        ImageView conf_sinc, conf_wifi, conf_dados, conf_bt, conf_gps, conf_som;
        TextView dia_dom, dia_seg, dia_ter, dia_qua, dia_qui, dia_sex, dia_sab, hora_inicio, hora_fim;
        SeekBar duracao;

        public ViewHolder(View view) {

            conf_sinc= (ImageView) view.findViewById(R.id.imageViewSinc);
            conf_wifi= (ImageView) view.findViewById(R.id.imageViewWifi);
            conf_dados= (ImageView) view.findViewById(R.id.imageViewDados);
            conf_bt= (ImageView) view.findViewById(R.id.imageViewBt);
            conf_gps= (ImageView) view.findViewById(R.id.imageViewGPS);
            conf_som= (ImageView) view.findViewById(R.id.imageViewSom);

            dia_dom	= (TextView) view.findViewById(R.id.textViewDom);
            dia_seg	= (TextView) view.findViewById(R.id.textViewSeg);
            dia_ter	= (TextView) view.findViewById(R.id.textViewTer);
            dia_qua	= (TextView) view.findViewById(R.id.textViewQua);
            dia_qui	= (TextView) view.findViewById(R.id.textViewQui);
            dia_sex	= (TextView) view.findViewById(R.id.textViewSex);
            dia_sab	= (TextView) view.findViewById(R.id.textViewSab);
            hora_inicio	= (TextView) view.findViewById(R.id.textViewInicio);
            hora_fim = (TextView) view.findViewById(R.id.textViewFim);

            duracao = (SeekBar) view.findViewById(R.id.seekBar);

        }

        public void setValues(Item item) {

            hora_inicio.setText(item.getHora_inicio());
            hora_fim.setText(item.getHora_fim());

            int colorId = R.color.colorAccent;

            if(item.isDia_dom()){
                dia_dom.setTextColor(activity.getResources().getColor(colorId));
            }

            if(item.isDia_seg()){
                dia_seg.setTextColor(activity.getResources().getColor(colorId));
            }

            if(item.isDia_ter()){
                dia_ter.setTextColor(activity.getResources().getColor(colorId));
            }

            if(item.isDia_qua()){
                dia_qua.setTextColor(activity.getResources().getColor(colorId));
            }

            if(item.isDia_qui()){
                dia_qui.setTextColor(activity.getResources().getColor(colorId));
            }

            if(item.isDia_sex()){
                dia_sex.setTextColor(activity.getResources().getColor(colorId));
            }

            if(item.isDia_sab()){
                dia_sab.setTextColor(activity.getResources().getColor(colorId));
            }


            int onId = R.drawable.switch_on;
            int offId = R.drawable.switch_off;

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
                conf_som.setImageResource(onId);
            }else {
                conf_som.setImageResource(offId);
            }

        }
    }

}

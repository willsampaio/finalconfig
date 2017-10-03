package com.example.finalconfig.finalconfig;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

/**
 * Created by willer_sampaio on 28/07/2017.
 */

public class ItemADO {
    private Item item;
    private Context context;
    private BancoDados db;
    private ArrayList<Item> listaItems;

    public ItemADO(Context context) {
        this.context = context;
        criarTabelaItem();
    }

    public void criarTabelaItem (){
        db = new BancoDados(context);
        db.getBanco().execSQL("CREATE TABLE IF NOT EXISTS item " +
                "                   (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "                   conf_sinc INTEGER, " +
                "                   conf_wifi INTEGER, " +
                "                   conf_dados INTEGER, " +
                "                   conf_bt INTEGER, " +
                "                   conf_gps INTEGER, " +
                "                   conf_som INTEGER, " +
                "                   dia_dom INTEGER, " +
                "                   dia_seg INTEGER, " +
                "                   dia_ter INTEGER, " +
                "                   dia_qua INTEGER, " +
                "                   dia_qui INTEGER, " +
                "                   dia_sex INTEGER, " +
                "                   dia_sab INTEGER, " +
                "                   hora_inicio TEXT, " +
                "                   hora_fim TEXT)");
    }

    private String boolToIntStr(boolean b){
        if(b){
            return "1";
        }else{
            return "0";
        }
    }

    private boolean intToBool(int i){
        if(i == 1){
            return true;
        }else{
            return false;
        }
    }

    public void inserirItem (Item i) {
        try {
            db.getBanco().execSQL("INSERT INTO item VALUES (NULL, ?, ?, ? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,?)",
                    new String[]{boolToIntStr(i.isConf_sinc()), boolToIntStr(i.isConf_wifi()), boolToIntStr(i.isConf_dados()), boolToIntStr(i.isConf_bt()),
                            boolToIntStr(i.isConf_gps()), boolToIntStr(i.isConf_som()), boolToIntStr(i.isDia_dom()), boolToIntStr(i.isDia_seg()),
                            boolToIntStr(i.isDia_ter()), boolToIntStr(i.isDia_qua()), boolToIntStr(i.isDia_qui()), boolToIntStr(i.isDia_sex()),
                            boolToIntStr(i.isDia_sab()), i.getHora_inicio(), i.getHora_fim()});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inserirItemId (Item i) {
        try {
            db.getBanco().execSQL("INSERT INTO item VALUES (?, ?, ? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,?)",
                    new String[]{i.isConf_sinc()+"", boolToIntStr(i.isConf_sinc()), boolToIntStr(i.isConf_wifi()), boolToIntStr(i.isConf_dados()), boolToIntStr(i.isConf_bt()),
                            boolToIntStr(i.isConf_gps()), boolToIntStr(i.isConf_som()), boolToIntStr(i.isDia_dom()), boolToIntStr(i.isDia_seg()),
                            boolToIntStr(i.isDia_ter()), boolToIntStr(i.isDia_qua()), boolToIntStr(i.isDia_qui()), boolToIntStr(i.isDia_sex()),
                            boolToIntStr(i.isDia_sab()), i.getHora_inicio(), i.getHora_fim()});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletarItem (Item i) {
        try {
            db.getBanco().execSQL("DELETE FROM item WHERE id = ?",
                    new String[]{""+i.getId()});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // deletar TODOS os items
    public void deletarItems () {
        try {
            db.getBanco().execSQL("DELETE FROM item");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void atualizarItem(Item s){
//        try {
//            db.getBanco().execSQL("UPDATE item SET titulo = ?, descricao = ? WHERE id = ?",
//                    new String[]{""+s.getTitulo(), s.getDescricao(),
//                            ""+s.getId()});
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    private ArrayList<Item> buscarItens(Cursor cursor){
        listaItems = new ArrayList<Item>();
        //Listar as tarefas
        cursor.moveToFirst();
        while (cursor != null) {
            item = new Item();
            item.setId(cursor.getInt(cursor.getColumnIndex("id")));
            item.setConf_sinc(intToBool(cursor.getColumnIndex("conf_sinc")));
            item.setConf_wifi(intToBool(cursor.getColumnIndex("conf_wifi")));
            item.setConf_dados(intToBool(cursor.getColumnIndex("conf_dados")));
            item.setConf_bt(intToBool(cursor.getColumnIndex("conf_bt")));
            item.setConf_gps(intToBool(cursor.getColumnIndex("conf_gps")));
            item.setConf_som(intToBool(cursor.getColumnIndex("conf_som")));
            item.setDia_dom(intToBool(cursor.getColumnIndex("dia_dom")));
            item.setDia_seg(intToBool(cursor.getColumnIndex("dia_seg")));
            item.setDia_ter(intToBool(cursor.getColumnIndex("dia_ter")));
            item.setDia_qua(intToBool(cursor.getColumnIndex("dia_qua")));
            item.setDia_qui(intToBool(cursor.getColumnIndex("dia_qui")));
            item.setDia_sex(intToBool(cursor.getColumnIndex("dia_sex")));
            item.setDia_sab(intToBool(cursor.getColumnIndex("dia_sab")));
            item.setHora_inicio(cursor.getString(cursor.getColumnIndex("hora_inicio")));
            item.setHora_fim(cursor.getString(cursor.getColumnIndex("hora_fim")));

            listaItems.add(item);
            cursor.moveToNext();
        }
        return listaItems;
    }

    public Item buscarItemId(Item i){
        try {

            Cursor cursor = db.getBanco().rawQuery("SELECT * FROM item WHERE id = ?",
                    new String[]{""+i.getId()});

            listaItems = buscarItens(cursor);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(listaItems.size() > 0) {
                return listaItems.get(0);
            }else{
                return null;
            }
        }
    }

    public ArrayList<Item> buscarItems(){
        Cursor cursor = null;
        try {

            cursor = db.getBanco().rawQuery("SELECT * FROM item ORDER BY id", null);

            listaItems = buscarItens(cursor);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            cursor.close();
            return listaItems;
        }
    }
}

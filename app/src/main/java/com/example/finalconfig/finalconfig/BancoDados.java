package com.example.finalconfig.finalconfig;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by willer_sampaio on 28/07/2017.
 */

public class BancoDados {

    private SQLiteDatabase banco;
    private final String NOMEBANCO = "APPF";
    private final Context context;

    public BancoDados(Context context){
        this.context = context;
        banco = context.openOrCreateDatabase(NOMEBANCO, Context.MODE_PRIVATE, null);
    }

    public SQLiteDatabase getBanco() {
        return banco;
    }

    public void setBanco(SQLiteDatabase banco) {
        this.banco = banco;
    }

}

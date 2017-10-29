package com.example.finalconfig.finalconfig;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    private ListView listViewItens;
    private ItemAdapter itemAdapter;
    private  ArrayList<Item> listItens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listViewItens = (ListView) findViewById(R.id.listViewMain);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        listViewItens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Item item = (Item)adapterView.getItemAtPosition(i);
                abrirPorId(item.getId());
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                abrirPorId(-1);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        listarItens();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_sobre) {
            startActivity(new Intent(MainActivity.this, SobreActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void listarItens(){
        ItemADO iado = new ItemADO(getApplicationContext());
        listItens = iado.buscarItems();
        itemAdapter = new ItemAdapter(listItens, this);
        listViewItens.setAdapter(itemAdapter);
    }

    private void abrirPorId(int id){
        Bundle bundle = new Bundle();
        bundle.putInt(getString(R.string.par_id), id);
        Intent intent = new Intent(MainActivity.this, ItemActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        startService(new Intent(MainActivity.this, Servico.class));
    }


}

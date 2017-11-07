package com.example.finalconfig.finalconfig;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.media.AudioManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.NotificationCompat;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * Created by will on 26/10/17.
 */

public class Servico extends Service {


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        criarNotificacao();

        new Thread(new Runnable(){
            @RequiresApi(api = Build.VERSION_CODES.N)
            public void run() {
                // TODO Auto-generated method stub
                while(true)
                {
                    try {
                        Thread.sleep(60000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    setConf();
                    criarNotificacao();
                }

            }
        }).start();

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    protected void criarNotificacao() {
        NotificationCompat.Builder mBuilder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.switch_on)
                        .setContentTitle("My notification")
                        .setContentText("Hello World!");

        int mNotificationId = 001;

        NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotifyMgr.notify(mNotificationId, mBuilder.build());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setConf(){
        ItemADO iado = new ItemADO(getApplicationContext());
        ArrayList<Item> lista = iado.buscarItems();
        String hr = getTimeSystem();

        for(Item item : lista){
            if(getDaysItem(item)[getDaySystem()] == 1){
                if(item.getHora_inicio().equals(hr)){
                    ativaDesativa(item);
                }
            }
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String getTimeSystem(){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date hora = Calendar.getInstance().getTime(); // Ou qualquer outra forma que tem
        return sdf.format(hora);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private int getDaySystem(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dia = Calendar.getInstance().getTime();
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(dia);
        return gc.get(GregorianCalendar.DAY_OF_WEEK);
    }

    private int[] getDaysItem(Item item){
        int[] dias = new int[7];

        if(item.isDia_dom()){
            dias[0] = 1;
        }

        if(item.isDia_seg()){
            dias[1] = 1;
        }

        if(item.isDia_ter()){
            dias[2] = 1;
        }

        if(item.isDia_qua()){
            dias[3] = 1;
        }

        if(item.isDia_qui()){
            dias[4] = 1;
        }

        if(item.isDia_sex()){
            dias[5] = 1;
        }

        if(item.isDia_sab()){
            dias[6] = 1;
        }

        return  dias;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void ativaDesativa(Item item){
//        if(item.isConf_sinc()){
//            conf_sinc.setImageResource(R.drawable.sinc1);
//        }else {
//            conf_sinc.setImageResource(R.drawable.sinc0);
//        }

        //wifi ------------------

        WifiManager wifiManager = (WifiManager)Servico.this.getSystemService(Context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(item.isConf_wifi());

        // dados -------------------
        TelephonyManager telephonyService = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        Method setMobileDataEnabledMethod = null;
        try {
            setMobileDataEnabledMethod = telephonyService.getClass().getDeclaredMethod("setDataEnabled", boolean.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        if (null != setMobileDataEnabledMethod)
        {
            try {
                setMobileDataEnabledMethod.invoke(telephonyService, item.isConf_dados());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        //bt ------------------------

        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(item.isConf_bt()) {
            mBluetoothAdapter.enable();
        }else{
            mBluetoothAdapter.disable();
        }

        //gps -----------------

        Intent intent = new Intent("android.location.GPS_ENABLED_CHANGE");
        intent.putExtra("enabled", item.isConf_gps());


        // som ------------------

        AudioManager am;
        am= (AudioManager) getBaseContext().getSystemService(Context.AUDIO_SERVICE);

        if(item.isConf_som()){
            am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        }else {
            am.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        }

//        //For Normal mode
//        am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
//
//        //For Silent mode
//        am.setRingerMode(AudioManager.RINGER_MODE_SILENT);
//
//        //For Vibrate mode
//        am.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
    }

}

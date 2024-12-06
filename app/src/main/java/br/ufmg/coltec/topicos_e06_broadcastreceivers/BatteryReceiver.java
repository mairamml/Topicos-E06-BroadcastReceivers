package br.ufmg.coltec.topicos_e06_broadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;

public class BatteryReceiver extends BroadcastReceiver {

    private final BatteryReceiverListener listener;

    public BatteryReceiver(BatteryReceiverListener listener) {
        this.listener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int nivelBateria = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scaleBateria = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        float porcentagem = nivelBateria/(float) scaleBateria;

        if (porcentagem <= 0.15) {
            listener.bateriaFraca();
        }else {
            listener.bateriaOk();
        }
    }

    public interface BatteryReceiverListener {
        void bateriaFraca();
        void bateriaOk();
    }

}

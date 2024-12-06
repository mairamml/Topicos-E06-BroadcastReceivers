package br.ufmg.coltec.topicos_e06_broadcastreceivers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static int CURRENT_THEME = R.style.Theme_TopicosE06BroadcastReceivers;

    // TODO: Criar os receivers para tratar a mudança do tema
    private BatteryReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTheme(CURRENT_THEME);
        setContentView(R.layout.activity_main);

        // TODO: registrar os receivers
        receiver = new BatteryReceiver((BatteryReceiver.BatteryReceiverListener) this);
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // TODO: desregistrar os receivers
        unregisterReceiver(receiver);
    }

    public void bateriaFraca() {
        if (CURRENT_THEME != R.style.Theme_TopicosE06BroadcastReceiversLowBattery) {
            switchActivityTheme(R.style.Theme_TopicosE06BroadcastReceiversLowBattery);
        }
    }

    public void bateriaOk() {
        if (CURRENT_THEME != R.style.Theme_TopicosE06BroadcastReceivers) {
            switchActivityTheme(R.style.Theme_TopicosE06BroadcastReceivers);
        }
    }

    /**
     * Realiza a troca do tema da Activity.
     *
     * Basicamente, esse método atualiza a variável estática responsável por definir o tema da
     * activity com o novo valor enviado por parâmetro, e recria toda a activity do zero para
     * que o novo tema seja aplicado.
     *
     * @param themeId Id do tema que será trocado.
     *                O parâmetro se refere ao ID que o tema possui na classe R.java.
     *                Para esse app específico, podemos informar dois possíveis IDs.
     *                - R.style.Theme_TopicosE06BroadcastReceivers (Tema padrão)
     *                - R.style.Theme_TopicosE06BroadcastReceiversLowBattery (bateria baixa)
     */
    private void switchActivityTheme(int themeId) {
        CURRENT_THEME = themeId;
        MainActivity.this.finish();
        MainActivity.this.startActivity(new Intent(MainActivity.this, MainActivity.class));
    }
}
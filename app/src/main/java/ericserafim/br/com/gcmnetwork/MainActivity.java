package ericserafim.br.com.gcmnetwork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.OneoffTask;
import com.google.android.gms.gcm.PeriodicTask;
import com.google.android.gms.gcm.Task;

import ericserafim.br.com.gcmnetwork.service.CustomService;

public class MainActivity extends AppCompatActivity {

    private GcmNetworkManager gcm;
    private Button button;
    private Button btnIniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gcm = GcmNetworkManager.getInstance(this);

        button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gcm.cancelAllTasks(CustomService.class);
            }
        });

        btnIniciar = (Button) findViewById(R.id.btnIniciar);
        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciar();
            }
        });

    }

    private void iniciar() {
    /* Exemplo de Bundle */
        Bundle bundle = new Bundle();
        bundle.putString("key", "valor de teste");

        PeriodicTask task = new PeriodicTask.Builder()
                .setTag("periodic")
                .setService(CustomService.class)
                .setPeriod(30)
                .setFlex(5)
                .setRequiredNetwork(Task.NETWORK_STATE_ANY)
                .setRequiresCharging(false)
                .setUpdateCurrent(true)
                .setPersisted(true)
                .setExtras(bundle)
                .build();

        gcm.schedule( task );
    }
}

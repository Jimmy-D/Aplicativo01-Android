package com.example.daniel.aplicativo01.Atividades;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.daniel.aplicativo01.R;

/**
 * Created by Daniel on 01/05/2017.
 */

public class Preencher extends Activity {
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.barra_preencher);

        LinearLayout lista = (LinearLayout) findViewById(R.id.caixas_selecao);
        final ProgressBar progresso = (ProgressBar) findViewById(R.id.progressBar);

        for(int i = 0; i<25; i++) {
            CheckBox check = new CheckBox(this);
            check.setText("Item " + i);
            check.setChecked(false);
            check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean checked =((CheckBox) v).isChecked();
                    if (checked)
                        progresso.incrementProgressBy(4);
                    else
                        progresso.incrementProgressBy(-4);
                    if (progresso.getProgress() == 100)
                        Toast.makeText(Preencher.this, "Completou", Toast.LENGTH_SHORT).show();
                }
            });
            lista.addView(check);
        }

    }
}

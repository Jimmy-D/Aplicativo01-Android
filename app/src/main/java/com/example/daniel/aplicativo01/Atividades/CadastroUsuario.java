package com.example.daniel.aplicativo01.Atividades;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.daniel.aplicativo01.R;

/**
 * Created by Daniel on 04/10/2017.
 */

public class CadastroUsuario extends Activity {
    private EditText    mEditUsuario;
    private EditText    mEditSenha;
    private Button      mButtonOk;

    private Context     mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_usuario);
        mContext = getApplicationContext();

        mEditUsuario = (EditText) findViewById(R.id.editUsuario);
        mEditSenha = (EditText) findViewById(R.id.editSenha);
        mButtonOk = (Button) findViewById(R.id.button2);

        mButtonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = mEditUsuario.getText().toString();
                String senha = mEditSenha.getText().toString();

                if (usuario.equals("") || senha.equals("")) {
                    Toast.makeText(mContext, "Todos os campos devem ser preenchidos!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putString("usuario", usuario);
                    bundle.putString("senha", senha);
                    Intent intent = new Intent(mContext, AcessoPorSenha.class);
                    intent.putExtras(bundle);
                    startNextActivity(intent);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(mContext, AcessoPorSenha.class);
        startNextActivity(intent);
    }

    public void startNextActivity(Intent intent) {
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }
}

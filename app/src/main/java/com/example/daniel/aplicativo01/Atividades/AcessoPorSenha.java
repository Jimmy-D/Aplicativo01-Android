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
 * Created by Daniel on 03/10/2017.
 */

public class AcessoPorSenha extends Activity{
    public String TAG = "Aplicativo01";

    private Intent mIntent;
    private Context mContext;

    private RepositorioUsuarios     mRepositorio;
    private EditText                mInsereUsuario;
    private EditText                mInsereSenha;
    private Button                  mButtonOk;
    private Button                  mButtonCadastro;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRepositorio = new RepositorioUsuarios(this);
        setContentView(R.layout.acesso_usuario);
        mContext = getApplicationContext();

        mIntent = getIntent();
        if (mIntent != null) {
            Bundle bundle = mIntent.getExtras();
            if (bundle != null) {
                if (mRepositorio.inserirNovoUsuario(bundle.getString("usuario"),
                        bundle.getString("senha")) == -1) {
                    Toast.makeText(this, "Nome de usuário já existente!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Novo usuário criado", Toast.LENGTH_SHORT).show();
                }
            }
        }

        if (!mRepositorio.listarUsuariosSenhas()) {
            Intent intent = new Intent(this, CadastroUsuario.class);
            startActivity(intent);
            finish();
        }

        mInsereUsuario = (EditText) findViewById(R.id.insereUsuario);
        mInsereSenha = (EditText) findViewById(R.id.insereSenha);
        mButtonOk = (Button) findViewById(R.id.button3);
        mButtonCadastro = (Button) findViewById(R.id.button);

        mButtonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = mInsereUsuario.getText().toString();
                String senha = mInsereSenha.getText().toString();

                if (mRepositorio.existeCadastro(usuario, senha)) {
                    Bundle bundle = new Bundle();
                    bundle.putString("usuario", usuario);
                    Intent intent = new Intent(mContext, EspacoUsuario.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    mInsereUsuario.setText("");
                    mInsereSenha.setText("");
                } else {
                    Toast.makeText(mContext, "Usuário ou senha incorretos!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mButtonCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CadastroUsuario.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mRepositorio.fechar();
    }
}

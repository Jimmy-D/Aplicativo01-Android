package com.example.daniel.aplicativo01.Atividades;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.daniel.aplicativo01.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Daniel on 07/10/2017.
 */

public class EspacoUsuario extends AppCompatActivity {
    public static String        TAG = "Aplicativo01";

    private String              mNomeArquivo;
    private EditText            mText;
    private FileOutputStream    mOutput;
    private String              mUsuario;
    private boolean             mDeletar;

    private RepositorioUsuarios mRepositorio;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRepositorio = new RepositorioUsuarios(this);
        setContentView(R.layout.texto_usuario);
        mDeletar = false;

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mUsuario = bundle.getString("usuario");
            mNomeArquivo = "APK" + mUsuario + ".txt";
            Log.i(TAG, "nome do arquivo: " + mNomeArquivo);
        } else {
            Log.e(TAG, "Os parâmetros não foram passados.");
            finish();
        }

        try {
            mOutput = openFileOutput(mNomeArquivo, MODE_APPEND);
        } catch (FileNotFoundException e) {
            Log.e(TAG, e.getMessage(), e);
            finish();
        }

        abrirTexto();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (!mDeletar) {
            try {
                String text = mText.getText().toString();
                deleteFile(mNomeArquivo);
                mOutput = openFileOutput(mNomeArquivo, MODE_APPEND);
                mOutput.write(text.getBytes());
                mOutput.close();
                Toast.makeText(this, "Texto salvo.", Toast.LENGTH_SHORT).show();
            } catch (FileNotFoundException e) {
                Log.e(TAG, e.getMessage(), e);
            } catch (IOException e) {
                Log.e(TAG, e.getMessage(), e);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_espaco_usuario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_apagar) {
            boolean ok = deleteFile(mNomeArquivo);
            Log.i(TAG,"Arquivo deletado? " + ok);
            mRepositorio.deletarUsuario(mUsuario);
            mDeletar = true;
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void abrirTexto() {
        mText = (EditText) findViewById(R.id.textoUsuario);
        try {
            File file = getFileStreamPath(mNomeArquivo);

            Log.i(TAG, "Abrindo arquivo: " + file.getAbsolutePath());

            if (file.exists()) {
                FileInputStream in = openFileInput(mNomeArquivo);
                int tamanho = in.available();
                byte bytes[] = new byte[tamanho];
                in.read(bytes);
                mText.setText(new String(bytes));
                in.close();
            } else {
                Log.i(TAG, "Arquivo não existe.");
                mText.setText("");
            }
        } catch (FileNotFoundException e) {
            Log.e(TAG, "Arquivo não encontrado: " + e.getMessage(), e);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }


}

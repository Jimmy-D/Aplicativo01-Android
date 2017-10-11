package com.example.daniel.aplicativo01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.daniel.aplicativo01.Atividades.AcessoPorSenha;
import com.example.daniel.aplicativo01.Atividades.Calculadora;
import com.example.daniel.aplicativo01.Atividades.Preencher;
import com.example.daniel.aplicativo01.Atividades.SlideFotos;

public class Menu_main extends AppCompatActivity {

    private static final String[] opcoes = new String[] {
            "Slide de fotos",
            "Preencher",
            "Calculadora",
            "Acesso",
            "Jogo",
            "Sair"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView lista = (ListView) findViewById(R.id.lista_menu);
        lista.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, opcoes));
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(Menu_main.this, SlideFotos.class));
                        break;
                    case 1:
                        startActivity(new Intent(Menu_main.this, Preencher.class));
                        break;
                    case 2:
                        startActivity(new Intent(Menu_main.this, Calculadora.class));
                        break;
                    case 3:
                        startActivity(new Intent(Menu_main.this, AcessoPorSenha.class));
                        break;
                    default:
                        finish();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(Menu_main.this, "apareceu", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*@Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        switch (position) {
            case 0:
                Toast.makeText(this, "yeeeeeey!!", Toast.LENGTH_SHORT).show();
        }
    }*/
}

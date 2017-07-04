package com.example.daniel.aplicativo01.Atividades;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.daniel.aplicativo01.R;

public class Calculadora extends Activity {
    private Button[] Bnum = new Button[10];
    private Button[] Bacao = new Button[7];
    private String stringVisor = "0";
    private char ultimaAcao = 'I';
    private boolean novoNumero = true;
    private boolean numDouble = false;
    private double ultimoNum = 0.0;
    private double numVisor = 0.0;
    final char[] acoes = {'.', 'I', 'C', '/', 'X', '-', '+'};

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Resources res = getResources();
        Configuration conf = res.getConfiguration();
        if(conf.orientation == Configuration.ORIENTATION_PORTRAIT)
            setContentView(R.layout.calculadora_portrait);
        else
            setContentView(R.layout.calculadora_landscape);

        final TextView visor = (TextView) findViewById(R.id.visor);

        if (savedInstanceState != null) {
            stringVisor = savedInstanceState.getString("calc");
        }

        visor.setText(stringVisor);

        //Criar botoes numericos
        Bnum[0] = (Button) findViewById(R.id.B0);
        Bnum[1] = (Button) findViewById(R.id.B1);
        Bnum[2] = (Button) findViewById(R.id.B2);
        Bnum[3] = (Button) findViewById(R.id.B3);
        Bnum[4] = (Button) findViewById(R.id.B4);
        Bnum[5] = (Button) findViewById(R.id.B5);
        Bnum[6] = (Button) findViewById(R.id.B6);
        Bnum[7] = (Button) findViewById(R.id.B7);
        Bnum[8] = (Button) findViewById(R.id.B8);
        Bnum[9] = (Button) findViewById(R.id.B9);

        //Criar botoes de ação
        Bacao[0] = (Button) findViewById(R.id.Bponto);
        Bacao[1] = (Button) findViewById(R.id.Bigual);
        Bacao[2] = (Button) findViewById(R.id.BC);
        Bacao[3] = (Button) findViewById(R.id.Bdiv);
        Bacao[4] = (Button) findViewById(R.id.Bmult);
        Bacao[5] = (Button) findViewById(R.id.Bmenos);
        Bacao[6] = (Button) findViewById(R.id.Bmais);

        //ações dos botões numericos
        for(byte i = 0; i < 10; i++) {
            final byte num = i;
            Bnum[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (stringVisor.equals("0") || novoNumero) {
                        stringVisor = "" + num;
                        novoNumero = false;
                    } else {
                        stringVisor += num;
                    }
                    visor.setText(stringVisor);
                }
            });
        }

        //ações dos botões de ação
        for(byte i = 0; i < 7; i++) {
            final byte num = i;
            Bacao[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    acao(num);
                    visor.setText(stringVisor);
                }
            });
        }

    }

    public void acao(byte bacao) {
        double numVisor = Double.parseDouble(stringVisor);
        switch (acoes[bacao]) {
            case '.':
                if (stringVisor.equals("0")) {
                    stringVisor = "0.";
                    numDouble = true;
                }
                if(!numDouble)
                    stringVisor += '.';
                numDouble = true;
                break;
            case 'I':
                if (ultimaAcao == 'I') {
                    novoNumero = true;
                    numDouble = false;
                    break;
                }
                numVisor = resultado(ultimaAcao);
                novoNumero = true;
                numDouble = false;
                ultimaAcao = 'I';
                stringVisor = "" + numVisor;
                break;
            case 'C':
                stringVisor = "0";
                ultimaAcao = 'I';
                novoNumero = true;
                numDouble = false;
                break;
            case '/':
                conta('/', numVisor);
                break;
            case 'X':
                conta('X', numVisor);
                break;
            case '-':
                conta('-', numVisor);
                break;
            case '+':
                conta('+', numVisor);
                break;
        }
    }

    public void conta(char caseAcao, double numVisor) {
        if(ultimaAcao != 'I')
            numVisor = resultado(ultimaAcao);
        ultimoNum = numVisor;
        novoNumero = true;
        numDouble = false;
        ultimaAcao = caseAcao;
        stringVisor = "" + numVisor;
    }

    public double resultado(char bacao) {
        numVisor = Double.parseDouble(stringVisor);
        switch (bacao) {
            case '/':
                if (numVisor == 0) {
                    stringVisor = "erro";
                    ultimaAcao = 'I';
                    novoNumero = true;
                    numDouble = false;
                    break;
                }
                numVisor = ultimoNum/numVisor;
                break;
            case 'X':
                numVisor = ultimoNum*numVisor;
                break;
            case '-':
                numVisor = ultimoNum-numVisor;
                break;
            case '+':
                numVisor = ultimoNum+numVisor;
                break;
        }
        return numVisor;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("APP", "App em Resume");
        Log.d("APP", "Valor de String Visor: " + stringVisor);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("APP", "App em Pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("APP", "App em Stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("APP", "App em Destroy");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("calc", stringVisor);
    }
}

package com.example.daniel.aplicativo01.Atividades;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Daniel on 06/10/2017.
 */

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String TAG = "Aplicativo01";

    private String mScriptSQLCreate;
    private String mScriptSQLDelete;

    public SQLiteHelper(Context context, String nomeBanco, int versaoBanco,
                        String scriptSQLcreate, String scriptSQLDelete) {
        super(context, nomeBanco, null, versaoBanco);
        mScriptSQLCreate = scriptSQLcreate;
        mScriptSQLDelete = scriptSQLDelete;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "Criando novo banco de dados \n" + mScriptSQLCreate);
        db.execSQL(mScriptSQLCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Atualizando a versão " + oldVersion + "para" + newVersion +
                "Todos os registros serão deletados.");
        Log.i(TAG, mScriptSQLDelete);
        db.execSQL(mScriptSQLDelete);
        onCreate(db);
    }
}

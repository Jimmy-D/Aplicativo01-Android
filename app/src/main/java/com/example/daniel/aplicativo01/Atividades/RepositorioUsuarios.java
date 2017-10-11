package com.example.daniel.aplicativo01.Atividades;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Daniel on 04/10/2017.
 */

public class RepositorioUsuarios {
    private static final String SCRIPT_DATABASE_DELETE = "DROP TABLE IF EXISTS usuario_senha";
    private static final String SCRIPT_DATABASE_CREATE =
            "create table usuario_senha (_id integer primary key autoincrement, " +
                    "usuario text not null unique, senha text not null)";

    public static final String  TAG = "Aplicativo01";
    public static final String  NOME_BANCO = "aplicativo";
    public static final String  NOME_TABELA = "usuario_senha";
    public static final int     VERSAO_BANCO = 1;

    public static String[]      colunas = {"usuario", "senha"};

    protected SQLiteDatabase    mDataBase;
    private SQLiteHelper        mDbHelper;

    public RepositorioUsuarios(Context context) {
        mDbHelper = new SQLiteHelper(context, NOME_BANCO, VERSAO_BANCO,SCRIPT_DATABASE_CREATE,
                SCRIPT_DATABASE_DELETE);
        mDataBase = mDbHelper.getWritableDatabase();
    }

    public long inserirNovoUsuario(String usuario, String senha) {
        ContentValues values = new ContentValues();
        values.put("usuario", usuario);
        values.put("senha", senha);
        long id = mDataBase.insert(NOME_TABELA, null, values);
        return id;
    }

    public int deletarUsuario(String usuario) {
        String where = "usuario=?";
        String[] whereArgs = new String[]{usuario};
        int count = mDataBase.delete(NOME_TABELA, where, whereArgs);
        return count;
    }

    public boolean listarUsuariosSenhas() {
        Cursor cursor = getCursor();

        if (cursor.moveToFirst()) {
            int idxUsuario = cursor.getColumnIndex("usuario");
            int idxSenha = cursor.getColumnIndex("senha");

            do {
                Log.d(TAG, String.format("%14s%14s", cursor.getString(idxUsuario), cursor.getString(idxSenha)));
            } while (cursor.moveToNext());
        } else {
            return false;
        }
        return true;
    }

    public boolean existeCadastro(String usuario, String senha) {
        Cursor cursor = getCursor();

        if (cursor.moveToFirst()) {
            int idxUsuario = cursor.getColumnIndex("usuario");
            int idxSenha = cursor.getColumnIndex("senha");

            do {
                if (cursor.getString(idxUsuario).equals(usuario)) {
                    if (cursor.getString(idxSenha).equals(senha)) {
                        return true;
                    }
                }
            } while (cursor.moveToNext());
        }
        return false;
    }

    public Cursor getCursor() {
        try {
            return mDataBase.query(NOME_TABELA, colunas, null, null, null, null, null, null);
        } catch (SQLException e) {
            Log.e(TAG, "Erro ao buscar os usuários: " + e.toString());
            return null;
        }
    }

    public void fechar() {
        if (mDataBase != null) {
            mDataBase.close();
        }
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }
}

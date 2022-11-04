package com.feldmann.projetofinalcdm.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.annotation.Nullable;

public class DBListas extends SQLiteOpenHelper {
    private static final String NOME_BANCO = "listas.db";
    private static final int VERSAO_BANCO = 1;

    public DBListas(@Nullable Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
        Log.d("DBListas", "Construtor");
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DBListas", "onCreate");
        db.execSQL( this.criarTabelaUsers() );
        //
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("DBListas", "onUpgrade");
        //
    }

    private String criarTabelaUsers(){
        String sqlStatement = "CREATE TABLE IF NOT EXISTS users("+
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT, " +
                "senha TEXT " +
                ");";
        return sqlStatement;
    }
    private String criarTabelaListaItens(){
        String sqlStatement = "CREATE TABLE IF NOT EXISTS itemList("+
                "_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "lista TEXT " +
                ");";
        return sqlStatement;
    }
}

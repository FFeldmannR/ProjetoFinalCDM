package com.feldmann.projetofinalcdm.controller;
//
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
//
public class DataBaseController implements Controller.controllerDataBase{
    @Override public String criarTabelaUsers() {
        String sqlStatement = "CREATE TABLE IF NOT EXISTS users("+
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT, " +
                "senha TEXT " +
                ");";
        return sqlStatement;
    }
    @Override public String criarTabelaListas() {
        String sqlStatement = "CREATE TABLE IF NOT EXISTS listas("+
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "donoLista TEXT, " +
                "nomeLista TEXT" +
                ");";
        return sqlStatement;
    }
    @Override public String criarTabelaCompras() {
        String sqlStatement = "CREATE TABLE IF NOT EXISTS compras("+
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "donoLista TEXT, " +
                "nomeLista TEXT, " +
                "nomeItem TEXT," +
                "quantidade TEXT," +
                "completed INTEGER" +
                ");";
        return sqlStatement;
    }
    @Override public void selectTable(SQLiteDatabase sqlRead, String nomeTabela) {
        Cursor cursor = sqlRead.rawQuery("SELECT * FROM "+nomeTabela, null);
        if (cursor.moveToFirst()){
            do {
                //
                if (nomeTabela.equals("users") ){
                    Log.d("SELECT_TABLE","_id | nome | senha"+
                            "("+Integer.valueOf(cursor.getString(0))+") "+
                            cursor.getString(1)+" | "+
                            cursor.getString(2) );
                }else if (nomeTabela.equals("lista") ){
                    Log.d("SELECT_TABLE","_id | donoLista | nomeLista"+
                            "("+Integer.valueOf(cursor.getString(0))+") "+
                            cursor.getString(1)+" | "+
                            cursor.getString(2) );
                }else if (nomeTabela.equals("compras") ){
                    Log.d("SELECT_TABLE","_id | nomeLista | nomeItem | quantidade | completed"+
                            "("+Integer.valueOf(cursor.getString(0))+") "+
                            cursor.getString(1)+" | "+
                            cursor.getString(2)+" | "+
                            cursor.getString(3)+" | "+
                            cursor.getString(4));
                }
            }while (cursor.moveToNext() );
        }else{
            Log.d("SELECT_TABLE", "NÃO TEM DADOS NESSA TABELA");
        }
    }//fim selectTable
}//fim class

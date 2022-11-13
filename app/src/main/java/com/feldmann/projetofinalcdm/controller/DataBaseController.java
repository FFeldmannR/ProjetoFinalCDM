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
}//fim class

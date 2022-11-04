package com.feldmann.projetofinalcdm.controller;

public class DataBaseController implements Controller.controllerDataBase{
    @Override
    public String criarTabelaUsers() {
        String sqlStatement = "CREATE TABLE IF NOT EXISTS users("+
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT, " +
                "senha TEXT " +
                ");";
        return sqlStatement;
    }

    @Override
    public String criarTabelaListas() {
        String sqlStatement = "CREATE TABLE IF NOT EXISTS listas("+
                "_id INTEGET PRIMARY KEY AUTOINCREMENT, "+
                "nomeLista"+
                ");";
        return sqlStatement;
    }
}

package com.feldmann.projetofinalcdm.controller;
//
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import com.feldmann.projetofinalcdm.repository.DBListas;
//
public class ViewController implements Controller.view{
    private final Context context;
    private final DBListas db;
    //
    public ViewController ( Context context ) {
        this.context = context;
        this.db = new DBListas( context );
    }
    @Override public Context getContext() {
        return this.context;
    }
    @Override public void selectTableDB(String nomeTabela) {
        Cursor cursor = db.getReadableDatabase().rawQuery("SELECT * FROM "+nomeTabela, null);
        Log.d("SELECT_TABLE", "");
        if (nomeTabela.equals("users") ){
            Log.d("SELECT_TABLE", "_id | nome | senha");
        }else if (nomeTabela.equals("listas") ){
            Log.d("SELECT_TABLE", "_id | donoLista | nomeLista");
        }else if (nomeTabela.equals("compras") ){
            Log.d("SELECT_TABLE","_id | donoLista | nomeLista | nomeItem | quantidade | completed");
        }
        if (cursor.moveToFirst()){
            do {
                if (nomeTabela.equals("users") ){
                    Log.d("SELECT_TABLE", ""+
                            "("+Integer.valueOf(cursor.getString(0))+") "+
                            cursor.getString(1)+" | "+
                            cursor.getString(2) );
                }else if (nomeTabela.equals("listas") ){
                    Log.d("SELECT_TABLE", ""+
                            "("+Integer.valueOf(cursor.getString(0))+") "+
                            cursor.getString(1)+" | "+
                            cursor.getString(2) );
                }else if (nomeTabela.equals("compras") ){
                    Log.d("SELECT_TABLE",""+
                            "("+Integer.valueOf(cursor.getString(0))+") "+
                            cursor.getString(1)+" | "+
                            cursor.getString(2)+" | "+
                            cursor.getString(3)+" | "+
                            cursor.getString(4)+" | "+
                            cursor.getString(5) );
                }
            }while (cursor.moveToNext() );
        }else{
            if (nomeTabela.equals("users") ){
                Log.d("SELECT_TABLE","NÃO TEM DADOS NESSA TABELA");
            }else if (nomeTabela.equals("listas") ){
                Log.d("SELECT_TABLE","NÃO TEM DADOS NESSA TABELA");
            }else if (nomeTabela.equals("compras") ){
                Log.d("SELECT_TABLE","NÃO TEM DADOS NESSA TABELA");
            }
        }//fim if else
    }//fim selectTableDB
}// fim class
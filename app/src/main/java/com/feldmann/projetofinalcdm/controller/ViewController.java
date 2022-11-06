package com.feldmann.projetofinalcdm.controller;
//
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.feldmann.projetofinalcdm.model.Listas;

//
public class ViewController implements Controller.view{
    private Context context;
    private Activity activity;
    //
    public ViewController(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }
    //
    @Override
    public Activity getActivity() {
        return this.activity;
    }
    @Override
    public Context getContext() {
        return this.context;
    }

    @Override
    public void getDataBase(String nomeTabela, SQLiteDatabase sqlRead) {
        Cursor cursor = sqlRead.rawQuery("SELECT * FROM "+nomeTabela, null);
        if (cursor.moveToFirst()){
            do {
                for (int i=0; i == cursor.getPosition(); i++){
                    Log.d("tagView", cursor.getString(i) );
                }
            }while (cursor.moveToNext());
        }else{
            Log.d("tagView", "NÃO TEM REGISTROS EM listas");
        }
        //
        cursor.close();
    }
}// fim class
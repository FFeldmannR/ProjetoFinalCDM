package com.feldmann.projetofinalcdm.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.feldmann.projetofinalcdm.controller.Controller;
import com.feldmann.projetofinalcdm.controller.MsgController;
import com.feldmann.projetofinalcdm.model.Listas;

import java.util.ArrayList;
import java.util.List;

public class ListasRepository {
    private static ListasRepository instance = null;
    private static List<Listas> listas;
    private static Controller.msg msg;

    public ListasRepository(Context context) {
        this.msg = new MsgController(context, this.getClass().getName().toString() );
        if (listas == null){
            listas = new ArrayList<>();
        }
    }
    //
    public static ListasRepository getInstance(Context context, SQLiteDatabase sqlWrite) {
        instance = new ListasRepository(context);
        listas.removeAll(getListas());
        //
        Cursor cursor = sqlWrite.rawQuery("SELECT * FROM listas", null);
        if (cursor.moveToFirst()){
            do {
                listas.add(new Listas(
                        Integer.parseInt( cursor.getString(0) ),
                        cursor.getString(1))
                );
                msg.logD("adicionado na lista");
            }while (cursor.moveToNext());
        }else{
            msg.logD("NÃO TEM REGISTROS EM listas");
        }
        //
        cursor.close();
        return instance;
    }
    //
    public static List<Listas> getListas() {
        return listas;
    }
}

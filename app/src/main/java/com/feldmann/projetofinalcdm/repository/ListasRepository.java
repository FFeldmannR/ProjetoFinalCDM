package com.feldmann.projetofinalcdm.repository;
//
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.feldmann.projetofinalcdm.adapters.ListaAdapter;
import com.feldmann.projetofinalcdm.controller.Controller;
import com.feldmann.projetofinalcdm.controller.MsgController;
import com.feldmann.projetofinalcdm.model.Listas;
import java.util.ArrayList;
import java.util.List;
//
public class ListasRepository {
    private static ListasRepository instance = null;
    private Context contexto;
    private static List<Listas> listas;
    private static Controller.msg msg;
    //
    public ListasRepository(Context context) {
        msg = new MsgController(context, this.getClass().getName().toString() );
        this.contexto = context;
        if (listas == null){
            listas = new ArrayList<>();
        }
    }
    //
    public static ListasRepository getInstanceListas(Context context, SQLiteDatabase sqlWrite) {
        instance = new ListasRepository(context);
        listas.removeAll(getListas());
        //
        Cursor cursor = sqlWrite.rawQuery("SELECT * FROM listas", null);
        if (cursor.moveToFirst()){
            do {
                listas.add(new Listas(
                        Integer.parseInt( cursor.getString(0) ),
                        cursor.getString(1),
                        cursor.getString(2) )
                );
                msg.logD(""+cursor.getString(2)+" adicionado no arrayList" );
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
    //
    public static void addToDB(ImageButton imgBtn, String donoLista, String nomeLista, SQLiteDatabase sqlWrite){
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                ContentValues ctv = new ContentValues();
                ctv.put("donoLista", donoLista);
                ctv.put("nomeLista", nomeLista);
                sqlWrite.insert("listas", null, ctv);
                msg.logD(""+nomeLista+" adicionada ao DB listas");
            }
        });
    }
    //
    public static void setAdapterListas(RecyclerView rv){
        msg.logD("setAdapterListas");
        ListaAdapter listaAdapter = new ListaAdapter( getListas() );
        rv.setAdapter(listaAdapter);
        rv.setLayoutManager( new LinearLayoutManager(instance.contexto ) );
    }
    //
}//fim classe

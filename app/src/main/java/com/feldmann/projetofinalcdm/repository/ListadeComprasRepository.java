package com.feldmann.projetofinalcdm.repository;
//
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.ImageButton;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.feldmann.projetofinalcdm.adapters.ListaAdapter;
import com.feldmann.projetofinalcdm.controller.Controller;
import com.feldmann.projetofinalcdm.controller.MsgController;
import com.feldmann.projetofinalcdm.model.Compras;
import com.feldmann.projetofinalcdm.model.Listas;
import com.feldmann.projetofinalcdm.views.ListaActivity;
import com.feldmann.projetofinalcdm.views.ListadeCompras;

import java.util.ArrayList;
import java.util.List;
//
public class ListadeComprasRepository {
    private static ListadeComprasRepository instance = null;
    private Context contexto;
    private static List<Compras> compras;
    private static Controller.msg msg;
    //
    public ListadeComprasRepository(Context contexto) {
        this.msg = new MsgController(contexto, this.getClass().getName() );
        this.contexto = contexto;
        if (instance == null){
            compras = new ArrayList<>();
        }
    }//fim construtor
    //
    public static ListadeComprasRepository getInstanceCompras(Context context, SQLiteDatabase sqlWrite, String nomeLista){
        instance = new ListadeComprasRepository(context);
        compras.removeAll( getCompras() );
        //
        Cursor cursor = sqlWrite.rawQuery("SELECT * FROM compras", null);
        if (cursor.moveToFirst()){
            do {
                //verifica em qual lista esta, para preencher o
                // arraylist apenas com as compras dessa lista
                if ( nomeLista.equals(cursor.getString(1) ) ){
                    compras.add(new Compras(
                            Integer.parseInt( cursor.getString(0) ),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4) )
                    );
                }
                //
                msg.logD(cursor.getString(2)+" adicionado no arrayList" );
            }while (cursor.moveToNext());
        }else{
            msg.logD("NÃO TEM REGISTROS EM listas");
        }
        //
        cursor.close();
        return instance;
    }
    //
    public static List<Compras> getCompras() {
        return compras;
    }
    //
    public static void insertItemToDB(
            ImageButton imgBtn, SQLiteDatabase sqlWrite,
            String nomeLista, String nomeItem,
            String qntd, String completed )
    {
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                ContentValues ctv = new ContentValues();
                ctv.put("donoLista", nomeLista);
                ctv.put("nomeLista", nomeItem);
                ctv.put("donoLista", qntd);
                ctv.put("nomeLista", completed);
                sqlWrite.insert("compras", null, ctv);
                msg.logD(nomeItem+" adicionado na lista de compras: "+nomeLista);
                //
                Intent in = new Intent(v.getContext(), ListadeCompras.class);
                in.putExtra("NOMELISTA", nomeLista);
                v.getContext().startActivity(in);
            }//fim onClick
        });//fim clickListener
    }//fim metodo insertItemToDB
    //
    public static void setAdapterItemList(RecyclerView rv){
        msg.logD("setAdapterListas");
        ComprasAdapter comprasAdapter = new ComprasAdapter( getCompras() );
        rv.setAdapter(comprasAdapter);
        rv.setLayoutManager( new LinearLayoutManager(instance.contexto ) );
    }
    //
}//fim classe

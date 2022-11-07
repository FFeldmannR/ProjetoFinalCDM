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
import com.feldmann.projetofinalcdm.model.Listas;
import com.feldmann.projetofinalcdm.views.ListaActivity;
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
    public static ListasRepository getInstanceListas(Context context, SQLiteDatabase sqlWrite, String userLogado) {
        instance = new ListasRepository(context);
        listas.removeAll(getListas());
        //
        Cursor cursor = sqlWrite.rawQuery("SELECT * FROM listas", null);
        if (cursor.moveToFirst()){
            do {
                //verifica usuario logado, para preencher o
                // arraylist apenas com as listas desse usuario
                if ( userLogado.equals(cursor.getString(1) ) ){
                    listas.add(new Listas(
                            Integer.parseInt( cursor.getString(0) ),
                            cursor.getString(1),
                            cursor.getString(2) )
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
    public static List<Listas> getListas() {
        return listas;
    }
    //
    public static void insertNewListToDB(ImageButton imgBtn, String donoLista, String nomeLista, SQLiteDatabase sqlWrite){
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                ContentValues ctv = new ContentValues();
                ctv.put("donoLista", donoLista);
                ctv.put("nomeLista", nomeLista);
                sqlWrite.insert("listas", null, ctv);
                msg.logD(nomeLista+" adicionada em listas");
                //
                Intent in = new Intent(v.getContext(), ListaActivity.class);
                in.putExtra("NOMEUSER", donoLista);
                v.getContext().startActivity(in);
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

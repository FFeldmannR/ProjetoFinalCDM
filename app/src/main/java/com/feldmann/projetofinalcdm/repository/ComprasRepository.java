package com.feldmann.projetofinalcdm.repository;
//
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.feldmann.projetofinalcdm.adapters.ComprasAdapter;
import com.feldmann.projetofinalcdm.controller.Controller;
import com.feldmann.projetofinalcdm.controller.MsgController;
import com.feldmann.projetofinalcdm.model.Compras;
import com.feldmann.projetofinalcdm.views.ListadeCompras;
import java.util.ArrayList;
import java.util.List;
//
public class ComprasRepository {
    private static ComprasRepository instance = null;
    private Context contexto;
    private static List<Compras> compras;
    private static Controller.msg msg;
    //
    public ComprasRepository(Context contexto) {
        this.msg = new MsgController(contexto, this.getClass().getName() );
        this.contexto = contexto;
        if (instance == null){
            compras = new ArrayList<>();
        }
    }//fim construtor
    //
    public static ComprasRepository getInstanceCompras(Context context, SQLiteDatabase sqlWrite, String nomeLista, String usuarioLogado){
        instance = new ComprasRepository(context);
        compras.removeAll( getCompras() );
        //
        Cursor cursor = sqlWrite.rawQuery("SELECT * FROM compras", null);
        if (cursor.moveToFirst()){
            do {
                //verifica qual a lista atual, para preencher o
                // arraylist apenas com as compras dessa lista
                int idDB = Integer.parseInt( cursor.getString(0) );
                String donoListaDB = cursor.getString(1);
                String nomeListaDB = cursor.getString(2);
                String nomeItemDB = cursor.getString(3);
                String quantidadeDB = cursor.getString(4);
                int completedDB = Integer.parseInt( cursor.getString(5) );
                if ( nomeLista.equals( nomeListaDB ) && usuarioLogado.equals( donoListaDB ) ){
                    compras.add(new Compras(
                            idDB,         // _id (INTEGER)
                            donoListaDB,  // donoLista (TEXT)
                            nomeListaDB,  // nomeLista (TEXT)
                            nomeItemDB,   // nomeItem (TEXT)
                            quantidadeDB,   // quantidade (TEXT)
                            completedDB     // completed (INTEGER)
                            )
                    );//fim compras.add
                    msg.logD("Adicionado no arrayList Compras: "+
                            "("+idDB+") dono: "+donoListaDB+" | Lista: "+nomeListaDB+" | Item: "+nomeItemDB+" ("+quantidadeDB+") "+"("+completedDB+")" );
                }

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
    public static void addItemToDB(
            SQLiteDatabase sqlWrite, Button btnSalvar, String usuarioLogado,
            String nomeLista, EditText etNomeItem,
            EditText etQntdItem, int completed) {
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues ctv = new ContentValues();
                ctv.put("donoLista", usuarioLogado);
                ctv.put("nomeLista", nomeLista);
                ctv.put("nomeItem", etNomeItem.getText().toString() );
                ctv.put("quantidade", etQntdItem.getText().toString() );
                ctv.put("completed", completed);
                sqlWrite.insert("compras", null, ctv);
                //
                Intent in = new Intent(v.getContext(), ListadeCompras.class);
                in.putExtra("NOMELISTA", nomeLista);
                in.putExtra("USUARIO", usuarioLogado);
                v.getContext().startActivity(in);
            }//fim onClick
        });//fim clickListener
    }//fim metodo insertItemToDB
    //
    public static void setAdapterItemList(RecyclerView rv, SQLiteDatabase sqlWrite, String lista){
        msg.logD("setAdapterListas");
        ComprasAdapter comprasAdapter = new ComprasAdapter( getCompras(), sqlWrite, lista );
        rv.setAdapter(comprasAdapter);
        rv.setLayoutManager( new LinearLayoutManager(instance.contexto ) );
    }
    //
}//fim classe

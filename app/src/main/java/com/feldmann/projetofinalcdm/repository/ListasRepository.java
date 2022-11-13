package com.feldmann.projetofinalcdm.repository;
//
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
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
    private Context contexto;
    private static ListasRepository instance = null;
    private static DBListas db;
    private static List<Listas> listas;
    private static Controller.msg msg;
    //
    public ListasRepository(Context context) {
        msg = new MsgController(context, this.getClass().getName().toString() );
        this.contexto = context;
        db = new DBListas( context );
        if (listas == null){ listas = new ArrayList<>(); }
    }
    //
    public static ListasRepository getInstanceListas(Context context, String userLogado) {
        instance = new ListasRepository(context);
        listas.removeAll(getListas());
        //
        Cursor cursor = db.getWritableDatabase().rawQuery("SELECT * FROM listas", null);
        if (cursor.moveToFirst()){
            do {
                //verifica usuario logado, para preencher o
                // arraylist apenas com as listas desse usuario
                if ( userLogado.equals(cursor.getString(1) ) ){
                    listas.add(new Listas(
                            Integer.parseInt( cursor.getString(0) ),
                            cursor.getString(1),
                            cursor.getString(2) )
                    );// fim add
                }//fim if
                msg.logD(cursor.getString(2)+" adicionado no arrayList" );
            }while (cursor.moveToNext());
        }else{ msg.logD("NÃO TEM REGISTROS EM listas"); }
        cursor.close();
        return instance;
    }
    public static List<Listas> getListas() {
        return listas;
    }
    //
    public static void createList(String donoLista, String nomeLista){
        try{
            ContentValues ctv = new ContentValues();
            ctv.put("donoLista", donoLista);
            ctv.put("nomeLista", nomeLista);
            db.getWritableDatabase().insert("listas", null, ctv);
            msg.logD(nomeLista+" adicionada em listas");
        }catch (SQLException sqlE){
            msg.logD("ERRO AO CRIAR LISTA\n"+sqlE.getMessage() );
        }//fim try catch
    }//fim insertNewListToDB
    public static void updateList( String donoLista, String nomeLista, String novoNomeLista ){
        try{
            db.getWritableDatabase().execSQL(
                    "UPDATE listas"+
                    " SET nomeLista='"+novoNomeLista+"'"+
                    " WHERE donoLista='"+donoLista+"'"+
                    " AND nomeLista='"+nomeLista+"'"
            );
            db.getWritableDatabase().execSQL(
                    "UPDATE compras"+
                    " SET nomeLista='"+novoNomeLista+"'"+
                    " WHERE donoLista='"+donoLista+"'"+
                    " AND nomeLista='"+nomeLista+"'"
            );
        }catch (SQLException sqlE){
            msg.logD("ERRO AO ATUALIZAR LISTA\n"+sqlE.getMessage() );
        }//fim try catch
    }//fim updateList
    public static void deleteList( String donoLista, String nomeLista ){
        try{
            db.getWritableDatabase().execSQL(
                    "DELETE FROM listas"+
                    " WHERE donoLista='"+donoLista+"'"+
                    " AND nomeLista='"+nomeLista+"'" );
            db.getWritableDatabase().execSQL(
                    "DELETE FROM compras"+
                    " WHERE donoLista='"+donoLista+"'"+
                    " AND nomeLista='"+nomeLista+"'" );
        }catch (SQLException e){
            msg.logD("ERRO AO DELETAR LISTA:\n"+e.getMessage() );
        }
    }
    public static void setAdapterListas(RecyclerView rv, String usuarioLogado){
        msg.logD("setAdapterListas");
        ListaAdapter listaAdapter = new ListaAdapter( getListas(), usuarioLogado );
        rv.setAdapter(listaAdapter);
        rv.setLayoutManager( new LinearLayoutManager(instance.contexto ) );
    }
}//fim classe

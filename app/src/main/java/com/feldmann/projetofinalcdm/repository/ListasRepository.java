package com.feldmann.projetofinalcdm.repository;
//
import android.content.*;
import android.database.*;
import android.graphics.Color;
import android.widget.TextView;
import com.feldmann.projetofinalcdm.R;
import com.feldmann.projetofinalcdm.controller.*;
import com.feldmann.projetofinalcdm.model.Listas;
import com.feldmann.projetofinalcdm.views.ListadeCompras;
import java.util.*;
//
public class ListasRepository {
    private static Context context;
    private static ListasRepository instance = null;
    private static DBListas db;
    private static List<Listas> listas;
    private static Controller.msg msg;
    //
    public ListasRepository(Context context) {
        this.context = context;
        db = new DBListas( context );
        if (listas == null){ listas = new ArrayList<>(); }
        msg = new MsgController(context, this.getClass().getName().toString() );
    }
    //
    public static ListasRepository getInstanceListas(Context context, String userLogado) {
        instance = new ListasRepository(context);
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
        }catch (SQLException sqlE){ msg.logD("ERRO AO CRIAR LISTA\n"+sqlE.getMessage() ); }//fim try catch
    }//fim insertNewListToDB
    public static void updateList(String donoLista, String nomeLista, String novoNomeLista, TextView tvNomeListaErrado ){
        Cursor cursor = db.getReadableDatabase().rawQuery("SELECT * FROM listas", null);
        if ( cursor.moveToFirst() ){
            do {
                if ( novoNomeLista.equals( cursor.getString(2) ) ){
                    //NOME DA LISTA JA EXISTE
                    tvNomeListaErrado.setText( context.getResources().getString(R.string.listaJaExiste) );
                    tvNomeListaErrado.setTextColor( Color.RED );
                }else{
                    try{
                        db.getWritableDatabase().execSQL(
                                "UPDATE listas"+
                                " SET nomeLista='"+novoNomeLista+"'"+
                                " WHERE donoLista='"+donoLista+"'"+
                                " AND nomeLista='"+nomeLista+"'" );
                        db.getWritableDatabase().execSQL(
                                "UPDATE compras"+
                                " SET nomeLista='"+novoNomeLista+"'"+
                                " WHERE donoLista='"+donoLista+"'"+
                                " AND nomeLista='"+nomeLista+"'" );
                        Intent intent = new Intent( context, ListadeCompras.class );
                        intent.putExtra("USUARIO", donoLista );
                        intent.putExtra("NOMELISTA", novoNomeLista );
                        context.startActivity( intent );
                    }catch (SQLException sqlE){ msg.logD("ERRO AO ATUALIZAR LISTA\n"+sqlE.getMessage() ); }//fim try catch
                }//fim if else
            }while ( cursor.moveToNext() );
        }else{
            msg.logD("NAO EXISTE DADOS NESSA TABELA");
        }
        cursor.close();
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
        }catch (SQLException e){ msg.logD("ERRO AO DELETAR LISTA:\n"+e.getMessage() ); }
    }
}//fim classe

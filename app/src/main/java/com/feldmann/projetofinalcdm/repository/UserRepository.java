package com.feldmann.projetofinalcdm.repository;
//
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.Color;
import android.widget.TextView;

import com.feldmann.projetofinalcdm.R;
import com.feldmann.projetofinalcdm.controller.Controller;
import com.feldmann.projetofinalcdm.controller.MsgController;
import com.feldmann.projetofinalcdm.views.ListaActivity;
import com.feldmann.projetofinalcdm.views.ListadeCompras;
import com.feldmann.projetofinalcdm.views.LoginActivity;
//
public class UserRepository {
    private static Context context;
    private static UserRepository instance = null;
    private static Controller.msg msg;
    private static DBListas db;
    //
    public UserRepository(Context context) {
        this.context = context;
        msg = new MsgController( context, this.getClass().getName() );
        db = new DBListas( context );
    }//fim construtor
    public static UserRepository getInstance(Context context) {
        instance = new UserRepository( context );
        return instance;
    }//fim getInstance
    public static void updateUser ( String nomeUser, String novoNomeUser, TextView tvUserIncorreto ){
        Cursor cursor = db.getReadableDatabase().rawQuery("SELECT * FROM listas", null);
        if ( cursor.moveToFirst() ){
            do {
                //
                if ( novoNomeUser.equals( cursor.getString(1) ) ){
                    //NOME DO USER JA EXISTE
                    tvUserIncorreto.setText( context.getResources().getString( R.string.usuarioJaExiste ) );
                    tvUserIncorreto.setTextColor( Color.RED );
                }else{
                    try{
                        db.getWritableDatabase().execSQL(
                                "UPDATE users"+
                                " SET nome='"+novoNomeUser+"'"+
                                " WHERE nome='"+nomeUser+"'" );
                        db.getWritableDatabase().execSQL(
                                "UPDATE listas"+
                                " SET donoLista='"+novoNomeUser+"'"+
                                " WHERE donoLista='"+nomeUser+"'" );
                        db.getWritableDatabase().execSQL(
                                "UPDATE compras"+
                                " SET donoLista='"+novoNomeUser+"'"+
                                " WHERE donoLista='"+nomeUser+"'" );
                        msg.messageToast("Nome alterado para '"+novoNomeUser+"'");
                        Intent intent = new Intent( context, ListaActivity.class );
                        intent.putExtra("USUARIO", novoNomeUser );
                        context.startActivity( intent );
                    }catch (SQLException sqlE){
                        msg.logD("ERRO AO ATUALIZAR USER\n"+sqlE.getMessage() );
                    }//fim try catch
                }//fim if else
            }while ( cursor.moveToNext() );
        }else{
            msg.logD("NAO EXISTE DADOS NESSA TABELA");
        }
        cursor.close();
    }
    public static void deleteUser ( String nomeUser ){
        try{
            db.getWritableDatabase().execSQL(
                    "DELETE FROM users"+
                    " WHERE nome='"+nomeUser+"'" );
            db.getWritableDatabase().execSQL(
                    "DELETE FROM listas"+
                    " WHERE donoLista='"+nomeUser+"'" );
            db.getWritableDatabase().execSQL(
                    "DELETE FROM compras"+
                    " WHERE donoLista='"+nomeUser+"'" );
            //
            Intent in = new Intent( context, LoginActivity.class );
            context.startActivity(in);
        }catch (SQLException e){
            msg.logD("ERRO AO DELETAR LISTA:\n"+e.getMessage() );
        }
    }
    public static void createUserinDB(String nomeUser, String senhaUser, TextView tvCadastroIncorreto ) {
        Cursor c = db.getReadableDatabase().rawQuery("SELECT * FROM users", null);
        if (c.moveToFirst()){
            do {
                if ( nomeUser.equals( c.getString(1) ) ){
                    tvCadastroIncorreto.setTextColor( Color.RED );
                    tvCadastroIncorreto.setText( context.getResources().getString(R.string.usuarioJaExiste) );
                }else {
                    cadastra( nomeUser, senhaUser, tvCadastroIncorreto );
                }
            }while (c.moveToNext());
        }else{
            cadastra( nomeUser, senhaUser, tvCadastroIncorreto );
        }
    }//fim createUserinDB
    private static void cadastra( String nomeUser, String senhaUser, TextView tvCadastroIncorreto ){
        if ( nomeUser.equals("") ) {
            msg.logD("usuario é nulo");
            tvCadastroIncorreto.setTextColor(Color.RED);
            tvCadastroIncorreto.setText(context.getResources().getString(R.string.usuarioVazio));
        }else if ( senhaUser.equals("") ) {
            msg.logD("senha é nulo");
            tvCadastroIncorreto.setTextColor( Color.RED );
            tvCadastroIncorreto.setText( context.getResources().getString(R.string.senhaVazia) );
        }else if ( nomeUser.length() < 3 ) {
            msg.logD("usuario < 3");
            tvCadastroIncorreto.setTextColor( Color.RED );
            tvCadastroIncorreto.setText( context.getResources().getString(R.string.usuarioPequeno));
        }else if ( senhaUser.length() <= 8) {
            msg.logD("senha <= 8");
            tvCadastroIncorreto.setTextColor( Color.RED );
            tvCadastroIncorreto.setText( context.getResources().getString(R.string.senhaPequena) );
        }else {
            msg.logD("PODE CADASTRAR");
            try{
                msg.logD("CADASTRAR");
                ContentValues ctv = new ContentValues();
                ctv.put("nome", nomeUser );
                ctv.put("senha", senhaUser );
                db.getWritableDatabase().insert("users", null, ctv);
                msg.messageToast(nomeUser + " ADICIONADO");
                Intent in = new Intent( context, LoginActivity.class );
                in.putExtra("NOMEUSER", nomeUser );
                context.startActivity(in);
            }catch (SQLException sqlE){
                msg.logD("ERRO AO ADICIONAR USUARIO\n"+sqlE.getMessage());
            }//fim try catch
        }//fim if else
    }
}//fim classe

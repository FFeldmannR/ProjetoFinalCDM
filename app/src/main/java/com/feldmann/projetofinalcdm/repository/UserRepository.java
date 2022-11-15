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
    public static void createUserinDB(String nomeUser, String senhaUser, TextView tvIncorreto){
        Cursor c = db.getReadableDatabase().rawQuery("SELECT * FROM users", null);
        if (c.moveToFirst()){
            //
            do {
                if ( nomeUser.equals( c.getString(1) ) ){
                    tvIncorreto.setTextColor(Color.RED);
                    tvIncorreto.setText(context.getResources().getString(R.string.usuarioJaExiste));
                }else{
                    if ( senhaUser.equals( c.getString(2) ) ){
                        try{
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
                        break;
                    }/*else {
                        tvIncorreto.setTextColor(Color.RED);
                        tvIncorreto.setText(context.getResources().getString(R.string.senhaIncorreta));
                    }*/
                }
            }while (c.moveToNext());
        }else{
            msg.logD("NÃO EXISTEM USUARIOS CADASTRADOS");
        }
        c.close();
    }//fim createUserinDB
}//fim classe

package com.feldmann.projetofinalcdm.controller;
//
import android.content.*;
import android.database.Cursor;
import android.graphics.Color;
import android.widget.TextView;
import com.feldmann.projetofinalcdm.R;
import com.feldmann.projetofinalcdm.repository.DBListas;
import com.feldmann.projetofinalcdm.views.ListaActivity;
//
public class LoginController implements Controller.controllerLogin{
    private final Context context;
    private final DBListas db;
    private static Controller.msg msg;
    //
    public LoginController(Context context) {
        this.context = context;
        this.db = new DBListas( context );
        msg = new MsgController(context, this.getClass().getName().toString() );
    }//fim contrutor
    @Override public void Login( String login, String senha, TextView tvIncorreto ) {
        verificaUser( login, senha, tvIncorreto );
    }//fim Login
    private void verificaUser( String login, String senha, TextView tvIncorreto ){
        Cursor cursor = db.getReadableDatabase().rawQuery("SELECT * FROM users", null);
        //
        if (cursor.moveToFirst()){
            do {
                if ( login.equals(cursor.getString(1)) ){
                    //usuario existe
                    if ( senha.equals(cursor.getString(2)) ){
                        //senha existe
                        Intent toListActivity = new Intent(context, ListaActivity.class);
                        toListActivity.putExtra("NOMEUSER", login );
                        msg.messageToast("Bem Vindo(a) "+login );
                        context.startActivity(toListActivity);
                        break;
                    }else{
                        //senha incorreta
                        tvIncorreto.setTextColor(Color.RED);
                        tvIncorreto.setText(context.getResources().getString(R.string.senhaIncorreta));
                    }
                }else {
                    //usuario nao existe
                    tvIncorreto.setTextColor(Color.RED);
                    tvIncorreto.setText(context.getResources().getString(R.string.usuarioIncorreto));
                }
            }while (cursor.moveToNext());
        }else{
            msg.logD("NÃO TEM REGISTROS DE USUARIOS");
        }//fim if else
        cursor.close();
    }//fim verificaUser
}//fim class
package com.feldmann.projetofinalcdm.controller;
//
import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
//
public class Controller {
    public interface view{
        public Activity getActivity();
        public Context getContext();
    }
    public interface msg{
        public void messageToast(String msg);
        public void logD(String msg);
    }
    public interface controllerLogin{
        public void setLoginField(String nomeUser);
        public void setTvEMS(TextView tvEMS);
        public void Login(Button btn, EditText etLogin, EditText etSenha);
        public void cadastrarUser(Button btn);
    }
    public interface controllerCadastro{
        public void getCampos(EditText etNome, EditText etSenha);
        public void addToDB(Button btnCadastrar, SQLiteDatabase sqlWrite);
    }
    public interface controllerListas {
        public void addList(ImageButton imgBtn);
        public void setAdapterListas(RecyclerView rv);
    }
}

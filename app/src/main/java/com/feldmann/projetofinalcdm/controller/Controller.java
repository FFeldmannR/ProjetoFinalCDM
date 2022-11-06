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
    public interface controllerInstance{
        public void instanceController();
    }
    public interface view{
        public Activity getActivity();
        public Context getContext();
    }
    public interface msg{
        public void messageToast(String msg);
        public void logD(String msg);
    }
    public interface controllerDataBase{
        public String criarTabelaUsers();
        public String criarTabelaListas();
    }
    public interface controllerLogin{
        public void setLoginField(String nomeUser, EditText etLoginL);
        public void setTvEMS(TextView tvEMS);
        public void Login(Button btn, EditText etLogin, EditText etSenha);
        public void cadastrarUser(Button btn);
    }
    public interface controllerCadastro{
        public void getCampos(EditText etNome, EditText etSenha);
        public void addToDB(Button btnCadastrar, SQLiteDatabase sqlWrite);
    }
    public interface controllerListas {
        public void addToDB(ImageButton imgBtn, String donoLista, String tamanhoDB);
        public void setAdapterListas(RecyclerView rv);
    }
}

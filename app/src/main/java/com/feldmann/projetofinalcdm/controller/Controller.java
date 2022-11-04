package com.feldmann.projetofinalcdm.controller;

import android.app.Activity;
import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.recyclerview.widget.RecyclerView;

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
        public void cadastrarUser(Button btn);
        public void verificaUser(Button btn);
        public void paraTelaLista(EditText etLogin, EditText etSenha);
    }
    public interface controllerCadastro{
        public void addToDB();
    }
    public interface controllerListas {
        public void addList(ImageButton imgBtn);
        public void setAdapterListas(RecyclerView rv);
    }
}

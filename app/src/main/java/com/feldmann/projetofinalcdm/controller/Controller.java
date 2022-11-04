package com.feldmann.projetofinalcdm.controller;

import android.app.Activity;
import android.content.Context;
import android.widget.Button;
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
        public void paraTelaLista(Button btn);
    }
    public interface controllerListas {
        public void addList(ImageButton imgBtn);
        public void setAdapterListas(RecyclerView rv);
    }
}

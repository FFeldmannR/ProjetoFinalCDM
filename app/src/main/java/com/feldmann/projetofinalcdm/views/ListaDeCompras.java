package com.feldmann.projetofinalcdm.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.feldmann.projetofinalcdm.R;
import com.feldmann.projetofinalcdm.controller.Controller;
import com.feldmann.projetofinalcdm.controller.MsgController;

public class ListaDeCompras extends AppCompatActivity implements Controller.view {
    private final String tagLog = this.getClass().getName().toString();
    private Controller.msg msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_compras);
        this.msg = new MsgController(getContext(), tagLog);
        msg.logD("onCreate");
    }

    @Override
    public Activity getActivity() { return this; }
    @Override
    public Context getContext() { return this; }
}
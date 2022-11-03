package com.feldmann.projetofinalcdm.views;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import com.feldmann.projetofinalcdm.R;
import com.feldmann.projetofinalcdm.controller.Controller;
import com.feldmann.projetofinalcdm.controller.ListasController;
import com.feldmann.projetofinalcdm.controller.MsgController;
import com.feldmann.projetofinalcdm.controller.ViewController;

public class ListasActivity extends AppCompatActivity{
    private final String tagLog = this.getClass().getName().toString();
    private Controller.msg msg;
    private Controller.view view;
    private Controller.controllerListas listas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listas);
        this.instanceController();
        msg.logD("onCreate");
    }

    @Override
    protected void onResume() {
        super.onResume();
        msg.logD("onResume");
        listas.paraListaDeCompras( ((ImageButton) findViewById(R.id.imgBtnAddList)) );
    }

    private void instanceController(){
        this.view = new ViewController(this, this);
        this.msg = new MsgController(view.getContext(), tagLog);
        this.listas = new ListasController(view.getContext());
    }
}
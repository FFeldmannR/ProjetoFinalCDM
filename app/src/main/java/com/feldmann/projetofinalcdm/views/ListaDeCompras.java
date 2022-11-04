package com.feldmann.projetofinalcdm.views;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.feldmann.projetofinalcdm.R;
import com.feldmann.projetofinalcdm.controller.Controller;
import com.feldmann.projetofinalcdm.controller.MsgController;
import com.feldmann.projetofinalcdm.controller.ViewController;

public class ListaDeCompras extends AppCompatActivity{
    private final String tagLog = this.getClass().getName().toString();
    private Controller.msg msg;
    private Controller.view view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_compras);
        this.instanceController();
        msg.logD("onCreate");
    }

    @Override
    protected void onResume() {
        super.onResume();
        msg.logD("onResume");
        this.setTitulo();
    }

    public void setTitulo(){
        if(getIntent().getStringExtra("ID") != null){
            //recebendo de listasController
            this.setTitle("Lista "+getIntent().getStringExtra("NOMELISTA") );
        }else if (getIntent().getStringExtra("NOMELISTA") != null){
            //recebendo de adapter
            this.setTitle(getIntent().getStringExtra("NOMELISTA") );
        }
    }

    private void instanceController(){
        this.view = new ViewController(this, this);
        this.msg = new MsgController(view.getContext(), tagLog);
    }
}
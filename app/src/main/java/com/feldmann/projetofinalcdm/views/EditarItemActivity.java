package com.feldmann.projetofinalcdm.views;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.feldmann.projetofinalcdm.*;
import com.feldmann.projetofinalcdm.controller.Controller;
import com.feldmann.projetofinalcdm.controller.EditItemController;
import com.feldmann.projetofinalcdm.controller.MsgController;
import com.feldmann.projetofinalcdm.controller.ViewController;
import com.feldmann.projetofinalcdm.model.Compras;
import com.feldmann.projetofinalcdm.repository.DBListas;

public class EditarItemActivity extends AppCompatActivity implements Controller.controllerInstance {
    private Controller.msg msg;
    private Controller.view view;
    Controller.controllerEditItem edit;
    private DBListas db;
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_item);
        this.instanceController();
        db = new DBListas(view.getContext());
        msg.logD("onCreate");
    }
    @Override protected void onResume() {
        super.onResume();
        msg.logD("onResume");
        Compras obj = getIntent().getParcelableExtra("OBJCOMPRAS");
    }
    @Override protected void onDestroy() {
        super.onDestroy();
        msg.logD("onDestroy");
    }
    @Override public void instanceController() {
        this.view = new ViewController(this, this);
        this.msg = new MsgController(view.getContext(), this.getClass().getName() );
        this.edit = new EditItemController();
    }
}
package com.feldmann.projetofinalcdm.views;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

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
        EditText etNomeItem = (EditText) findViewById(R.id.etNomeItemEdit);
        EditText etQntdItem = (EditText) findViewById(R.id.etQuantidadeEdit);
        msg.logD("nomeItem: "+obj.getNomeItem() );
        etNomeItem.setText( obj.getNomeItem() );
        etQntdItem.setText( obj.getQuantidade() );
        edit.updateItem( (Button) findViewById(R.id.btnSalvarEdit),
                db.getWritableDatabase(),
                etNomeItem, etQntdItem,
                obj );
        edit.deleteItem( (Button) findViewById(R.id.btnDeleteItem),
                db.getWritableDatabase(), obj.getDonoLista(),
                obj.getNomeLista(), obj.getNomeItem() );
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
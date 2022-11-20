package com.feldmann.projetofinalcdm.views;
//
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.feldmann.projetofinalcdm.*;
import com.feldmann.projetofinalcdm.controller.ComprasAdapterController;
import com.feldmann.projetofinalcdm.controller.Controller;
import com.feldmann.projetofinalcdm.controller.EditItemController;
import com.feldmann.projetofinalcdm.controller.MsgController;
import com.feldmann.projetofinalcdm.controller.ViewController;
import com.feldmann.projetofinalcdm.model.Compras;
//
public class EditarItemActivity extends AppCompatActivity implements Controller.controllerInstance {
    private Compras obj;
    private Controller.msg msg;
    private Controller.view view;
    private Controller.controllerEditItem edit;
    //
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_item);
        this.instanceController();
        msg.logD("onCreate");
    }//fim onCreate
    @Override protected void onResume() {
        super.onResume();
        msg.logD("onResume");
        this.toolBar(obj);


            // METODOS PARA OS CAMPOS DE TEXTO
        EditText etNomeItem = (EditText) findViewById(R.id.etNomeItemEdit);
        EditText etQntdItem = (EditText) findViewById(R.id.etQuantidadeEdit);
        etNomeItem.setText( obj.getNomeItem() );
        etQntdItem.setText( obj.getQuantidade() );

            // METODO PARA O BOTAO SALVAR
        ((FrameLayout) findViewById(R.id.FLbtnSalvarItem)).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                edit.updateItem(etNomeItem.getText().toString(), etQntdItem.getText().toString(), obj );
            }//fim onClick
        });//fim clickListener
            // METODOS DO BOTAO DELETAR
        ((FrameLayout) findViewById(R.id.FLbtnDeleteItem)).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                edit.deleteItem( obj );
            }//fim onClick
        });//fim clickListener
    }//fim onResume
    @Override protected void onDestroy() {
        super.onDestroy();
        msg.logD("onDestroy");
    }//fim onDestroy
    @Override public void instanceController() {
        this.view = new ViewController(this );
        this.msg = new MsgController(view.getContext(), this.getClass().getName() );
        this.edit = new EditItemController( view.getContext() );
        obj = getIntent().getParcelableExtra("OBJCOMPRAS");
    }// instanceController
            // METODOS PARA OS BOTOES VOLTAR
    private void toolBar(Compras compras){
        setTitle( "Editar Item: "+compras.getNomeItem() );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }//fim toolBar()
    @Override public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(this, ListadeCompras.class);
            intent.putExtra("USUARIO", obj.getDonoLista());
            intent.putExtra("NOMELISTA", obj.getNomeLista());
            startActivity(intent);
            finishAffinity();
        }//fim switch
        return true;
    }//fim onOptionsItemSelected
    @Override public void onBackPressed() {
        try {
            Intent intent = new Intent(this, ListadeCompras.class);
            intent.putExtra("USUARIO", obj.getDonoLista() );
            intent.putExtra("NOMELISTA", obj.getNomeLista() );
            startActivity( intent );
            finishAffinity();
        }catch (Exception e){
            msg.logD("FALHA AO VOLTAR\nERRO::: "+e.getMessage());
        }//fim try catch
    }//fim onBackPressed
}//fim classe
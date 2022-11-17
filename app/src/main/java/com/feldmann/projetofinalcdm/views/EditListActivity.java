package com.feldmann.projetofinalcdm.views;
//
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.feldmann.projetofinalcdm.R;
import com.feldmann.projetofinalcdm.controller.*;
//
public class EditListActivity extends AppCompatActivity implements Controller.controllerInstance {
    private Controller.msg msg;
    private Controller.view view;
    private Controller.controllerEditList lista;
    private String usuarioLogado, nomeLista;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_list);
        this.instanceController();
        msg.logD("onCreate");
    }//fim onCreate
    @Override protected void onResume() {
        super.onResume();
        this.toolBar();
        msg.logD("onResume");
        usuarioLogado = getIntent().getStringExtra("USUARIOLOGADO");
        nomeLista = getIntent().getStringExtra("NOMELISTA");
        //
        EditText etNomeLista = ((EditText) findViewById(R.id.etNomeListaEdit));
        etNomeLista.setText(nomeLista);
        //
        ((FrameLayout) findViewById(R.id.FLbtnSalvarList)).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                lista.updateList( usuarioLogado, nomeLista, etNomeLista.getText().toString(), ((TextView) findViewById(R.id.tvNomeListaErrado)) );
            }//fim onClick
        });//fim clickListener
        ((FrameLayout) findViewById(R.id.FLbtnDeleteList)).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                lista.deleteList( usuarioLogado, nomeLista );
            }
        });
    }//fim onResume
    @Override protected void onDestroy() {
        super.onDestroy();
        msg.logD("onDestroy");
    }//fim onDestroy
    @Override public void instanceController() {
        this.view = new ViewController(this, this);
        this.msg = new MsgController( view.getContext(), this.getClass().getName() );
        this.lista = new ListasController( view.getContext() );
    }//fim instanceController
            // METODOS PARA OS BOTOES VOLTAR
    private void toolBar(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }//fim toolBar()
    @Override public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch ( item.getItemId() ) {
            case android.R.id.home:
                Intent intent = new Intent(this, ListaActivity.class);
                intent.putExtra("NOMEUSER", usuarioLogado);
                startActivity( intent );
                finish();
                break;
        }//fim switch
        return true;
    }//fimonOptionsItemSelected
    @Override public void onBackPressed() {
        try {
            Intent intent = new Intent(this, ListaActivity.class);
            intent.putExtra("NOMEUSER", usuarioLogado);
            startActivity( intent );
            finishAffinity();
        }catch (Exception e){
            msg.logD("FALHA AO VOLTAR\nERRO::: "+e.getMessage());
        }//fim try catch
    }//fim onBackPressed
}//fim class
package com.feldmann.projetofinalcdm.views;
//
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;
import com.feldmann.projetofinalcdm.R;
import com.feldmann.projetofinalcdm.controller.Controller;
import com.feldmann.projetofinalcdm.controller.MsgController;
import com.feldmann.projetofinalcdm.controller.ViewController;
import com.feldmann.projetofinalcdm.repository.DBListas;
import com.feldmann.projetofinalcdm.repository.ListasRepository;
//
public class ListaActivity extends AppCompatActivity implements Controller.controllerInstance{
    private Controller.msg msg;
    private Controller.view view;
    private DBListas db;
    //
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listas);
        this.instanceController();
        db = new DBListas(view.getContext());
        msg.logD("onCreate");
    }//fim onCreate

    @Override protected void onResume() {
        super.onResume();
        msg.logD("onResume");
        this.toolBar();
        String usuario = getIntent().getStringExtra("NOMEUSER");
        setTitle(usuario);

        ListasRepository.getInstanceListas(view.getContext(), db.getWritableDatabase(), usuario );
        ListasRepository.insertNewListToDB(
                ((ImageButton) findViewById(R.id.imgBtnAddList)),
                usuario,
                getNomeLista(),//Lista + (databaseSize+1)
                db.getWritableDatabase()
        );
        //
        ListasRepository.setAdapterListas( (RecyclerView) findViewById(R.id.RVListas), usuario );
        //
    }//fim onResume
    @Override protected void onDestroy() {
        super.onDestroy();
        msg.logD("onDestroy");
    }
    //
    @Override public void instanceController() {
        this.view = new ViewController(this, this);
        this.msg = new MsgController(view.getContext(), this.getClass().getName().toString() );
    }//fim instanceController
    private String getNomeLista(){
        return "Lista "+Integer.toString( ( ListasRepository.getListas().size() ) +1 );
    }
    private void toolBar(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }
    //
    @Override public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity( intent );
                finishAffinity();
                break;
        }
        return true;
    }
    @Override public void onBackPressed() {
        try {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity( intent );
            finishAffinity();
        }catch (Exception e){
            msg.logD("FALHA AO VOLTAR\nERRO::: "+e.getMessage());
        }
    }
}//fim class
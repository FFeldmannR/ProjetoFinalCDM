package com.feldmann.projetofinalcdm.views;
//
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import com.feldmann.projetofinalcdm.R;
import com.feldmann.projetofinalcdm.controller.*;
import com.feldmann.projetofinalcdm.repository.*;
//
public class ListadeCompras extends AppCompatActivity implements Controller.controllerInstance{
    private Controller.msg msg;
    private Controller.view view;
    private Controller.controllerAdapters adapters;
    private DBListas db;
    private String usuarioLogado, nomeLista;
    //
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_compras);
        this.instanceController();
        db = new DBListas(view.getContext());
        msg.logD("onCreate");
    }//fim onCreate
    @Override protected void onResume() {
        super.onResume();
        msg.logD("onResume");
        this.toolBar();
        usuarioLogado = getIntent().getStringExtra("USUARIO");
        nomeLista = getIntent().getStringExtra("NOMELISTA");
        setTitle(nomeLista);
        //
        ComprasRepository.getInstanceCompras(
                view.getContext(), nomeLista, usuarioLogado );
        //metodo para activity de criar item
        this.cadastrarItem( (ImageButton) findViewById(R.id.imgBtnAddItem), nomeLista );
        //
        adapters.setAdapterItemList(
                (RecyclerView) findViewById(R.id.RVCompras),
                ComprasRepository.getCompras(),
                db.getWritableDatabase(),
                nomeLista
        );
    }//fim onResume
    @Override protected void onDestroy() {
        super.onDestroy();
        msg.logD("onDestroy");
    }//fim onDestroy
    private void cadastrarItem(ImageButton imgBtn, String nomeLista){
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent in = new Intent(v.getContext(), CadastrarItemActivity.class);
                in.putExtra("USUARIOLOGADO", usuarioLogado);
                in.putExtra("NOMELISTA", nomeLista);
                v.getContext().startActivity(in);
            }//fim onClick
        });//fim clickListener
    }//fim cadastrarItem
    @Override public void instanceController() {
        this.view = new ViewController(this, this);
        this.msg = new MsgController(view.getContext(), this.getClass().getName() );
        this.adapters = new AdapterController( view.getContext() );
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
            msg.logD("usuarioLogado: "+usuarioLogado);
            intent.putExtra("NOMEUSER", usuarioLogado);
            startActivity( intent );
            finishAffinity();
        }catch (Exception e){
            msg.logD("FALHA AO VOLTAR\nERRO::: "+e.getMessage());
        }//fim try catch
    }//fim onBackPressed
}//fim class
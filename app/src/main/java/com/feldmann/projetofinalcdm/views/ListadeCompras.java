package com.feldmann.projetofinalcdm.views;
//
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import com.feldmann.projetofinalcdm.R;
import com.feldmann.projetofinalcdm.controller.Controller;
import com.feldmann.projetofinalcdm.controller.MsgController;
import com.feldmann.projetofinalcdm.controller.ViewController;
import com.feldmann.projetofinalcdm.repository.DBListas;
import com.feldmann.projetofinalcdm.repository.ComprasRepository;
//
public class ListadeCompras extends AppCompatActivity implements Controller.controllerInstance{
    private Controller.msg msg;
    private Controller.view view;
    private DBListas db;
    //
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_compras);
        this.instanceController();
        db = new DBListas(view.getContext());
        msg.logD("onCreate");
    }
    //
    @Override protected void onResume() {
        super.onResume();
        msg.logD("onResume");
        this.toolBar();
        String nomeLista = getIntent().getStringExtra("NOMELISTA");
        setTitle(nomeLista);
        //
        ComprasRepository.getInstanceCompras(
                view.getContext(), db.getWritableDatabase(), nomeLista );
        //metodo para activity de criar item
        this.cadastrarItem((ImageButton) findViewById(R.id.imgBtnAddItem), nomeLista );
        //
        ComprasRepository.setAdapterItemList(
                (RecyclerView) findViewById(R.id.RVCompras),
                db.getWritableDatabase(),
                nomeLista
        );
    }//fim onResume
    @Override protected void onDestroy() {
        super.onDestroy();
        msg.logD("onDestroy");
    }
    //
    private void cadastrarItem(ImageButton imgBtn, String nomeLista){
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(v.getContext(), CadastrarItemActivity.class);
                in.putExtra("NOMELISTA", nomeLista);
                v.getContext().startActivity(in);
            }
        });
    }//fim cadastrarItem
    @Override public void instanceController() {
        this.view = new ViewController(this, this);
        this.msg = new MsgController(view.getContext(), this.getClass().getName() );
    }
    private void toolBar(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }
    @Override public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                startActivity(new Intent(this, ListaActivity.class) );
                finish();
                break;
        }
        return true;
    }
    @Override public void onBackPressed() {
        startActivity(new Intent(this, ListaActivity.class));
        finishAffinity();
        return;
    }
}//fim class
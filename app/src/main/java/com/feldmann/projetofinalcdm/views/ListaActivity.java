package com.feldmann.projetofinalcdm.views;
//
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
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
    private ListasRepository listas;
    private DBListas db;
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listas);
        this.instanceController();
        this.listas = new ListasRepository(view.getContext(), db.getWritableDatabase() );
        db = new DBListas(view.getContext());
        msg.logD("onCreate");
    }//fim onCreate

    @Override
    protected void onResume() {
        super.onResume();
        msg.logD("onResume");
        //chamar instancia de listas
    }//fim onResume
    @Override
    public void instanceController() {
        this.view = new ViewController(this, this);
        this.msg = new MsgController(view.getContext(), this.getClass().getName().toString() );
    }//fim instanceController
}//fim class
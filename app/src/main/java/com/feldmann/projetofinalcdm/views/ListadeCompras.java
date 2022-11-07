package com.feldmann.projetofinalcdm.views;
//
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.feldmann.projetofinalcdm.R;
import com.feldmann.projetofinalcdm.controller.Controller;
import com.feldmann.projetofinalcdm.controller.MsgController;
import com.feldmann.projetofinalcdm.controller.ViewController;
import com.feldmann.projetofinalcdm.repository.DBListas;
import com.feldmann.projetofinalcdm.repository.ListadeComprasRepository;
//
public class ListadeCompras extends AppCompatActivity implements Controller.controllerInstance{
    private Controller.msg msg;
    private Controller.view view;
    private DBListas db;
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_compras);
        this.instanceController();
        db = new DBListas(view.getContext());
        msg.logD("onCreate");
    }
    //
    @Override
    protected void onResume() {
        super.onResume();
        msg.logD("onResume");
        String nomeLista = getIntent().getStringExtra("NOMELISTA");
        setTitle(nomeLista);
        //
        ListadeComprasRepository.getInstanceCompras(
                view.getContext(), db.getWritableDatabase(), nomeLista );
        //metodo para activity de criar item
    }
    //
    @Override
    public void instanceController() {
        this.view = new ViewController(this, this);
        this.msg = new MsgController(view.getContext(), this.getClass().getName() );
    }
}
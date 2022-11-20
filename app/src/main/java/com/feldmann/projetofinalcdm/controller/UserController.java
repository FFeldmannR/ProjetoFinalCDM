package com.feldmann.projetofinalcdm.controller;
//
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;
import com.feldmann.projetofinalcdm.repository.UserRepository;
import com.feldmann.projetofinalcdm.views.MainActivity;
//
public class UserController implements Controller.controllerUsers {
    private final Context context;
    //
    public UserController(Context context) {
        this.context = context;
    }
    //
    @Override public void updateUser (String nomeUser, String novoNomeUser, TextView tvUserIncorreto ) {
        UserRepository.updateUser( nomeUser, novoNomeUser, tvUserIncorreto );
    }
    @Override public void deleteUser ( String nomeUser ) {
        UserRepository.deleteUser ( nomeUser );
        context.startActivity( new Intent( context, MainActivity.class ) );
    }
}

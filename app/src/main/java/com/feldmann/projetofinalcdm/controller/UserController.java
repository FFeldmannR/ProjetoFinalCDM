package com.feldmann.projetofinalcdm.controller;

import android.widget.TextView;

import com.feldmann.projetofinalcdm.repository.UserRepository;

public class UserController implements Controller.controllerUsers {
    @Override public void updateUser ( String nomeUser, String novoNomeUser, TextView tvUserIncorreto ) {
        UserRepository.updateUser( nomeUser, novoNomeUser, tvUserIncorreto );
    }
    @Override public void deleteUser ( String nomeUser ) {
        UserRepository.deleteUser ( nomeUser );
    }
}

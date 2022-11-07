package com.feldmann.projetofinalcdm.repository;

import android.content.Context;
import com.feldmann.projetofinalcdm.controller.Controller;
import com.feldmann.projetofinalcdm.model.Listas;
import java.util.List;

public class ListadeComprasRepository {
    private static ListasRepository instance = null;
    private Context contexto;
    private static List<Listas> listas;
    private static Controller.msg msg;
}

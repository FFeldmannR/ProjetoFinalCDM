package com.feldmann.projetofinalcdm.adapters;
//
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.feldmann.projetofinalcdm.R;
import com.feldmann.projetofinalcdm.model.Listas;
import java.util.List;
//
public class ListaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //
    private List<Listas> listas;
    //
    public ListaAdapter(List<Listas> listas) {
        this.listas = listas;
    }
    //
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutViewHolder = LayoutInflater.from(parent.getContext()).inflate(R.layout.modelo_lista_layout, parent, false);
        return new ListaViewHolder(layoutViewHolder);
    }
    //
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Listas objListas = listas.get(position);
        ((TextView)((ListaViewHolder) holder).view.findViewById(R.id.tvNomeLista)).setText( "Lista "+objListas.getId() );
        //
    }
    //
    @Override
    public int getItemCount() {
        return listas.size();
    }
}
class ListaViewHolder extends RecyclerView.ViewHolder{
    public View view;
    public ListaViewHolder(@NonNull View itemView){
        super(itemView);
        view = itemView;
    }
}
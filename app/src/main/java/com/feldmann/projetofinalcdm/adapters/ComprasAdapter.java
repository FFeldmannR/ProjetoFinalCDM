package com.feldmann.projetofinalcdm.adapters;
//
import android.content.Context;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.feldmann.projetofinalcdm.R;
import com.feldmann.projetofinalcdm.controller.ComprasAdapterController;
import com.feldmann.projetofinalcdm.model.Compras;
import java.util.List;
//
public class ComprasAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<Compras> comprasList;
    private final String nomeListaAtual;
    private ComprasAdapterController CAC;
    //
    public ComprasAdapter ( Context context, List<Compras> comprasList, String nomeListaAtual ) {
        this.comprasList = comprasList;
        this.nomeListaAtual = nomeListaAtual;
        this.CAC = new ComprasAdapterController ( context );
    }
    @NonNull @Override public RecyclerView.ViewHolder onCreateViewHolder ( @NonNull ViewGroup parent, int viewType ) {
        View layoutViewHolder = LayoutInflater.from ( parent.getContext() ).inflate ( R.layout.modelo_item_layout, parent, false );
        return new ComprasViewHolder ( layoutViewHolder );
    }
    @Override public void onBindViewHolder ( @NonNull RecyclerView.ViewHolder holder, int position ) {
        Compras objCompras = comprasList.get ( position );
        //
        ((TextView)((ComprasViewHolder) holder).view.findViewById(R.id.tvNomeItem)).setText( objCompras.getNomeItem() );
        ((TextView)((ComprasViewHolder) holder).view.findViewById(R.id.tvQuantidade)).setText( objCompras.getQuantidade() );
        //checkbox
        CAC.updateCheckbox( holder, ( (CheckBox)((ComprasViewHolder) holder).view.findViewById(R.id.checkBoxItem) ),
                nomeListaAtual, objCompras );
        CAC.clickInfoItemToEdit(
                ( (LinearLayout)((ComprasViewHolder) holder).itemView.findViewById(R.id.LLinfoItem) ),
                holder, objCompras );
    }//fim bindView
    @Override public int getItemCount() {
        return comprasList.size();
    }
}// fim classe
class ComprasViewHolder extends RecyclerView.ViewHolder{
    public View view;
    public ComprasViewHolder(@NonNull View itemView){
        super(itemView);
        view = itemView;
    }
}//fim classe
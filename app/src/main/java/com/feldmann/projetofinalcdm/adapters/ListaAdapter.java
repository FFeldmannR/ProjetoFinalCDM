package com.feldmann.projetofinalcdm.adapters;
//
import android.content.*;
import android.os.Build;
import android.util.TypedValue;
import android.view.*;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.feldmann.projetofinalcdm.R;
import com.feldmann.projetofinalcdm.model.Compras;
import com.feldmann.projetofinalcdm.model.Listas;
import com.feldmann.projetofinalcdm.views.ListadeCompras;
import java.util.List;
//
public class ListaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Listas> listas;
    private String usuarioLogado;
    //
    public ListaAdapter ( List<Listas> listas, String usuarioLogado ) {
        this.listas = listas;
        this.usuarioLogado = usuarioLogado;
    }
    @NonNull @Override public RecyclerView.ViewHolder onCreateViewHolder ( @NonNull ViewGroup parent, int viewType ) {
        View layoutViewHolder = LayoutInflater.from ( parent.getContext() ).inflate ( R.layout.modelo_lista_layout, parent, false );
        return new ListaViewHolder ( layoutViewHolder );
    }
    @Override public void onBindViewHolder ( @NonNull RecyclerView.ViewHolder holder, int position ) {
        Listas objListas = listas.get ( position );
        //
        ((TextView)((ListaViewHolder) holder).view.findViewById(R.id.tvNomeLista)).setText( objListas.getNomeLista() );
        //
        holder.itemView.setOnClickListener ( new View.OnClickListener() {
            @Override public void onClick ( View v ) {
                buttonEffect ( v.getContext(), holder );
                paraListadeCompras ( v.getContext(), objListas.getNomeLista() );
            }//fim onClick
        });//fim clickListener
    }//fim bindView
    public void addList ( Listas listas ) {
        this.listas.add( listas );
        this.notifyDataSetChanged();
    }
    @Override public int getItemCount () { return listas.size(); }
    //
    private void buttonEffect ( Context context, RecyclerView.ViewHolder holder ) {
        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ) {
            TypedValue outValue = new TypedValue ();
            context.getTheme().resolveAttribute ( android.R.attr.selectableItemBackground, outValue, true );
            holder.itemView.setBackgroundResource ( outValue.resourceId );
        }
    }//fim buttonEffect
    private void paraListadeCompras ( Context context, String nomeLista ) {
        Intent in = new Intent ( context, ListadeCompras.class );
        in.putExtra ("USUARIO", usuarioLogado );
        in.putExtra ("NOMELISTA", nomeLista );
        context.startActivity ( in );
    }
}//fim classe
class ListaViewHolder extends RecyclerView.ViewHolder {
    public View view;
    public ListaViewHolder ( @NonNull View itemView ) {
        super ( itemView );
        view = itemView;
    }
}
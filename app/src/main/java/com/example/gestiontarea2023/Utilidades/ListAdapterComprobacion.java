package com.example.gestiontarea2023.Utilidades;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.gestiontarea2023.Model.Comprobacion;
import com.example.gestiontarea2023.R;
import com.example.gestiontarea2023.View.ModalComprobacionDialog;
import java.util.List;

public class ListAdapterComprobacion extends RecyclerView.Adapter<ListAdapterComprobacion.ViewHolder>{

    private LayoutInflater layoutInflater;
    private List<Comprobacion> listaComprobacion;
    private Context context;
    private Activity activity;
    private ModalComprobacionDialog modalComprobacionDialog;
    public ListAdapterComprobacion(List<Comprobacion> listaComprobacion, Context context, ModalComprobacionDialog modalComprobacionDialog){
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.listaComprobacion = listaComprobacion;
        this.activity = (Activity) context;
        this.modalComprobacionDialog = modalComprobacionDialog;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_comprobacion,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapterComprobacion.ViewHolder holder, int position) {
        Comprobacion comprobacion  = listaComprobacion.get(position);
        holder.titulo_comprobacion.setText(comprobacion.getTitulo_comprobacion());
        holder.btn_check_comprobacion.setChecked(comprobacion.getEstado_comprobante());
        holder.bindData(comprobacion);
    }

    @Override
    public int getItemCount() {
        return listaComprobacion.size();
    }

    public int getItemsRealizados(List<Comprobacion> listaComprobacion){
        int cantidad = 0;
        for (int i = 0; i < listaComprobacion.size() ; i ++){
            if(listaComprobacion.get(i).getEstado_comprobante()){
                cantidad++;
            }
        }
        return cantidad;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titulo_comprobacion;
        CheckBox btn_check_comprobacion;
        ImageButton btn_eliminar_comprobacion;
        ViewHolder(View holder) {
            super(holder);
            titulo_comprobacion = holder.findViewById(R.id.titulo_comprobacion);
            btn_check_comprobacion = holder.findViewById(R.id.btn_check_comprobacion);
            btn_eliminar_comprobacion = holder.findViewById(R.id.btn_eliminar_comprobacion);
        }
        void bindData(Comprobacion comprobacion){
            btn_eliminar_comprobacion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    modalComprobacionDialog.Eliminar(comprobacion);
                    listaComprobacion.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                }
            });
            btn_check_comprobacion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    comprobacion.setEstado_comprobante(btn_check_comprobacion.isChecked());
                    modalComprobacionDialog.CheckComprobante(comprobacion);
                }
            });
        }
    }
    public void setItems(List<Comprobacion> items){
        listaComprobacion = items;
    }

}
//notifyDataSetChanged();
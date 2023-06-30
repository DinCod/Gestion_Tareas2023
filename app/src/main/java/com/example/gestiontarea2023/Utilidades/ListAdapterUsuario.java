package com.example.gestiontarea2023.Utilidades;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.gestiontarea2023.Model.Usuario;
import com.example.gestiontarea2023.R;
import com.example.gestiontarea2023.View.ModalUsuarioDialog;

import java.util.List;

public class ListAdapterUsuario extends RecyclerView.Adapter<ListAdapterUsuario.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<Usuario> listaUsuarios;
    private Context context;
    private Activity activity;
    private ModalUsuarioDialog modalUsuarioDialog;
    public ListAdapterUsuario(List<Usuario> itemList, Context context, ModalUsuarioDialog modalUsuarioDialog) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.listaUsuarios = itemList;
        this.activity = (Activity) context;
        this.modalUsuarioDialog = modalUsuarioDialog;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_usuario, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Usuario usuario = listaUsuarios.get(position);
        holder.txtNombre.setText(usuario.getNombre()+" "+usuario.getApellido_paterno()+" "+usuario.getApellido_materno());
        holder.txtEmail.setText(usuario.getCorreo());
        holder.bindData(usuario);
    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre, txtEmail;
        ViewHolder(View holder) {
            super(holder);
            txtNombre = holder.findViewById(R.id.txt_nombre_usuario);
            txtEmail  = holder.findViewById(R.id.txt_correo_usuario);
        }
        void bindData(Usuario usuario){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    modalUsuarioDialog.CrearModalButtom(usuario);
                }
            });
        }
    }

    public void setItems(List<Usuario> items) {
        listaUsuarios = items;
    }

}

package com.example.gestiontarea2023.View;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestiontarea2023.Model.Invitacion;
import com.example.gestiontarea2023.Model.Tablero;
import com.example.gestiontarea2023.Model.Usuario;
import com.example.gestiontarea2023.R;
import com.example.gestiontarea2023.Utilidades.DialogInvitacion;
import com.example.gestiontarea2023.Utilidades.ListAdapterUsuario;
import com.example.gestiontarea2023.ViewModel.InvitacionViewModel;
import com.example.gestiontarea2023.ViewModel.UsuarioViewModel;

import java.util.List;

public class ModalUsuarioDialog extends DialogFragment {

    private Usuario usuario;
    private Tablero tablero;
    private Context context;
    private UsuarioViewModel usuarioViewModel;
    private InvitacionViewModel invitacionViewModel;
    private ListAdapterUsuario listAdapterUsuario;
    private DialogInvitacion customDialog;
    private Invitacion invitacion;
    public ModalUsuarioDialog(Tablero tablero){
        this.tablero = tablero;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.modal_usuario,container,false);
        setRetainInstance(true);
        usuarioViewModel = new UsuarioViewModel(context);
        usuarioViewModel.getListaUsuarioLiveData().observe(getViewLifecycleOwner(), new Observer<List<Usuario>>() {
            @Override
            public void onChanged(List<Usuario> usuarios) {
                listAdapterUsuario = new ListAdapterUsuario(usuarios,context,ModalUsuarioDialog.this);
                RecyclerView recyclerView = view.findViewById(R.id.lista_recicleview_usuarios);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                recyclerView.setAdapter(listAdapterUsuario);
                listAdapterUsuario.setItems(usuarios);
            }
        });
        invitacionViewModel = new InvitacionViewModel(context);
        invitacionViewModel.getRegistro().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean response) {
                if(response){
                    message("Solicitud enviada");
                    customDialog.dismiss();
                }
            }
        });
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onResume() {
        super.onResume();
        usuarioViewModel.Lista(tablero.getId_usuario());
    }

    public void message(String mensaje){
        Toast.makeText(context,mensaje,Toast.LENGTH_SHORT).show();
    }

    public void CrearModalButtom(Usuario usuario){
        customDialog = new DialogInvitacion(context);
        customDialog.show(usuario.getCorreo(), new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id_usuario_emisor = tablero.getId_usuario();
                int id_usuario_receptor = usuario.getId_usuario();
                int id_tablero = tablero.getId_tablero();
                String tipo_invitacion = customDialog.getSelectedItem();
                if(id_usuario_emisor<=0 || id_usuario_receptor<=0 || id_tablero<=0 || tipo_invitacion.isEmpty()){
                    message("Complete todos los campos.");
                }else{
                    invitacion = new Invitacion(id_usuario_emisor,id_usuario_receptor,id_tablero,tipo_invitacion);
                    invitacionViewModel.Registro(invitacion);
                }
            }
        });
    }
}

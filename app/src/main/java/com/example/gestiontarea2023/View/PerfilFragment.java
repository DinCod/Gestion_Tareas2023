package com.example.gestiontarea2023.View;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.gestiontarea2023.Model.Usuario;
import com.example.gestiontarea2023.R;
import com.google.android.material.textfield.TextInputLayout;

public class PerfilFragment extends Fragment {

    private Usuario usuario;
    private Context context;

    private TextInputLayout txtNombre,txtDni,txtEmail,txtPassword,txtNumeroTelefono;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            usuario = (Usuario) getArguments().getSerializable("usuario");
        }
    }

    public void message(String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tercero, container, false);
        getActivity().setTitle("Mi Perfil");
        txtNombre         = view.findViewById(R.id.textInputName2);
        txtDni            = view.findViewById(R.id.textInputDni2);
        txtNumeroTelefono = view.findViewById(R.id.textInputMobile2);
        txtEmail          = view.findViewById(R.id.textInputEmail2);
        txtPassword       = view.findViewById(R.id.textInputPassword2);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        txtNombre.getEditText().setText(usuario.getNombre()+" "+usuario.getApellido_paterno()+" "+usuario.getApellido_materno());
        txtDni.getEditText().setText(usuario.getDni());
        txtEmail.getEditText().setText(usuario.getCorreo());
        txtPassword.getEditText().setText(usuario.getClave());
        txtNumeroTelefono.getEditText().setText(usuario.getTelefono());
    }
}
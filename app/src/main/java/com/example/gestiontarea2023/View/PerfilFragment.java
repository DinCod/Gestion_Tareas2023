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

public class PerfilFragment extends Fragment {

    private Usuario usuario;
    private Context context;

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
        message(usuario.getCorreo());
        getActivity().setTitle("Mi Perfil");
        return inflater.inflate(R.layout.fragment_tercero, container, false);
    }
}
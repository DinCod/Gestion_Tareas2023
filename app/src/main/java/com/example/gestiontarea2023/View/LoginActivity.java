package com.example.gestiontarea2023.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.gestiontarea2023.Model.Usuario;
import com.example.gestiontarea2023.R;
import com.example.gestiontarea2023.Utilidades.Progress;
import com.example.gestiontarea2023.ViewModel.UsuarioViewModel;
import com.google.android.material.textfield.TextInputLayout;
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private TextInputLayout textInputEmail, textInputPassword;
    private Button btnLogin;
    private UsuarioViewModel usuarioViewModel;
    private Progress progress;
    private Context context;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.context      = this;
        textInputEmail    = findViewById(R.id.textInputEmail);
        textInputPassword = findViewById(R.id.textInputPassword);
        btnLogin          = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
        progress          = new Progress(context);
        activity          = (Activity) context;
        usuarioViewModel = new UsuarioViewModel(context);
        usuarioViewModel.getUsuarioLiveData().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                if (usuario != null) {
                    context.startActivity(new Intent(context,MenuActivity.class).putExtra("usuario",usuario));
                    activity.overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
                    activity.finishAffinity();
                }else{
                    message("Verifique su usuario o contraseña.");
                }
            }
        });
    }
    public void message(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public void OnClickRegister(View view){
        startActivity(new Intent(this, RegistroActivity.class));
        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
    }

    public void OnClickRegister2(View view){
        startActivity(new Intent(this, RegistroActivity.class));
        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        String email    = textInputEmail.getEditText().getText().toString().trim();
        String password = textInputPassword.getEditText().getText().toString().trim();
        textInputEmail.getEditText().setText(email);
        textInputPassword.getEditText().setText(password);
    }

    @Override
    public void onClick(View view) {
        if (btnLogin == view) {
            String email = textInputEmail.getEditText().getText().toString().trim();
            String password = textInputPassword.getEditText().getText().toString().trim();
            if (email.isEmpty() || password.isEmpty()) {
                message("Complete todos los campos.");
            } else {
                usuarioViewModel.login(email,password);
            }
        }
    }
}

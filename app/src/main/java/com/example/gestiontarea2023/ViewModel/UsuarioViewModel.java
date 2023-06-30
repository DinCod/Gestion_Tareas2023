package com.example.gestiontarea2023.ViewModel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.gestiontarea2023.Conexion.Servidor;
import com.example.gestiontarea2023.Model.Usuario;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsuarioViewModel extends ViewModel {
    private MutableLiveData<Usuario> usuarioLiveData = new MutableLiveData<>();
    public LiveData<Usuario> getUsuarioLiveData() {
        return usuarioLiveData;
    }
    private MutableLiveData<Boolean> registroExitoso = new MutableLiveData<>();
    public LiveData<Boolean> getRegistroExitoso() {return registroExitoso;}
    private MutableLiveData<List<Usuario>> listaUsuarioLiveData = new MutableLiveData<>();
    public LiveData<List<Usuario>> getListaUsuarioLiveData() {
        return listaUsuarioLiveData;
    }
    private Context context;
    private Servidor conexion = new Servidor();
    public UsuarioViewModel(Context context){
        this.context = context;
    }
    private Usuario usuario;

    public void login(String correo, String clave) {
        RequestQueue queue = Volley.newRequestQueue(context.getApplicationContext());
        String url = conexion.url_local() + "usuario.php?accion=1&correo=" + correo + "&clave=" + clave;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Usuario usuario = null;
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                usuario = new Usuario();
                                usuario.setId_usuario(jsonObject.getInt("id_usuario"));
                                usuario.setNombre(jsonObject.getString("nombre"));
                                usuario.setApellido_materno(jsonObject.getString("apellido_materno"));
                                usuario.setApellido_paterno(jsonObject.getString("apellido_paterno"));
                                usuario.setCorreo(jsonObject.getString("correo"));
                                usuarioLiveData.setValue(usuario);
                            }
                            if(usuario == null){
                                usuarioLiveData.setValue(null);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Error en la conexión.", Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(jsonArrayRequest);
    }

    public void Registro(Usuario usuario) {
        RequestQueue queue = Volley.newRequestQueue(context.getApplicationContext());
        String url = conexion.url_local() + "usuario.php?accion=2";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("true")){
                            registroExitoso.setValue(true);
                        }else{
                            Toast.makeText(context, "Lo sentimos, el correo ingresado ya se encuentra en uso.", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        registroExitoso.setValue(false);
                        Toast.makeText(context, "Error al registrar.", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("nombre", usuario.getNombre());
                params.put("apellido_paterno", usuario.getApellido_paterno());
                params.put("apellido_materno", usuario.getApellido_materno());
                params.put("dni", usuario.getDni());
                params.put("telefono", usuario.getTelefono());
                params.put("correo", usuario.getCorreo());
                params.put("clave", usuario.getClave());
                return params;
            }
        };
        queue.add(stringRequest);
    }

    public void Lista(int id_usuario) {
        RequestQueue queue = Volley.newRequestQueue(context.getApplicationContext());
        String url = conexion.url_local() + "usuario.php?accion=3&id_usuario=" + id_usuario;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        List<Usuario> listUsuario = new ArrayList<>();
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                usuario = new Usuario();
                                usuario.setId_usuario(jsonObject.getInt("id_usuario"));
                                usuario.setNombre(jsonObject.getString("nombre"));
                                usuario.setApellido_materno(jsonObject.getString("apellido_materno"));
                                usuario.setApellido_paterno(jsonObject.getString("apellido_paterno"));
                                usuario.setCorreo(jsonObject.getString("correo"));
                                listUsuario.add(usuario);
                            }
                            listaUsuarioLiveData.setValue(listUsuario);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Error en la conexión.", Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(jsonArrayRequest);
    }

}

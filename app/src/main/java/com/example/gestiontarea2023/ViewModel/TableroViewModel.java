package com.example.gestiontarea2023.ViewModel;

import android.content.Context;
import android.widget.Toast;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.gestiontarea2023.Conexion.Servidor;
import com.example.gestiontarea2023.Model.Tablero;
import com.example.gestiontarea2023.Model.Tarea;
import com.example.gestiontarea2023.Model.Usuario;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableroViewModel {

    private Context context;
    private Servidor conexion = new Servidor();
    private Tablero tablero;
    private Usuario usuario;
    private MutableLiveData<Boolean> registro = new MutableLiveData<>();
    public LiveData<Boolean> getRegistro() {return registro;}
    private MutableLiveData<List<Tablero>> listaTableroLiveData = new MutableLiveData<>();
    public LiveData<List<Tablero>> getListaTableroLiveData() { return listaTableroLiveData;}
    private MutableLiveData<Boolean> editar = new MutableLiveData<>();
    public LiveData<Boolean> getEditar() {return editar;}
    public TableroViewModel(Context context) {
        this.context = context;
    }
    public Usuario ReturnUsuario(Usuario usuario) {
        this.usuario = usuario;
        return this.usuario;
    }
    public Tablero ReturnTablero(Tablero tablero) {
        this.tablero = tablero;
        return  this.tablero;
    }
    public void Registro(Tablero tablero) {
        RequestQueue queue = Volley.newRequestQueue(context.getApplicationContext());
        String url = conexion.url_local() + "tablero.php?accion=1";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("true")){
                            registro.setValue(true);
                            Lista();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        registro.setValue(false);
                        Toast.makeText(context, "Error al registrar.", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id_usuario", String.valueOf(tablero.getId_usuario()));
                params.put("nombre_tablero", tablero.getNombre_tablero());
                return params;
            }
        };
        queue.add(stringRequest);
    }

    public void Lista(){
        RequestQueue queue = Volley.newRequestQueue(context.getApplicationContext());
        String url = conexion.url_local() + "tablero.php?accion=2&id_usuario=" + usuario.getId_usuario();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        List<Tablero> listTablero = new ArrayList<>();
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                tablero = new Tablero();
                                tablero.setId_tablero(jsonObject.getInt("id_tablero"));
                                tablero.setId_usuario(jsonObject.getInt("id_usuario"));
                                tablero.setNombre_tablero(jsonObject.getString("nombre_tablero"));
                                tablero.setEstado(jsonObject.getInt("estado"));
                                listTablero.add(tablero);
                            }
                            calcularCantidadTareasPorTablero(listTablero);
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

    public void Editar(String nombre_tablero){
        RequestQueue queue = Volley.newRequestQueue(context.getApplicationContext());
        String url = conexion.url_local() + "tablero.php?accion=3";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("true")) {
                            editar.setValue(true);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        editar.setValue(false);
                        Toast.makeText(context, "Error al editar.", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id_tablero", String.valueOf(tablero.getId_tablero()));
                params.put("nombre_tablero", nombre_tablero);
                return params;
            }
        };
        queue.add(stringRequest);
    }

    public void calcularCantidadTareasPorTablero(List<Tablero> listTablero) {
        for (Tablero tablero : listTablero) {
            RequestQueue queue = Volley.newRequestQueue(context.getApplicationContext());
            String url = conexion.url_local() + "tablero.php?accion=4&id_tablero=" + tablero.getId_tablero();
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                int numTareas = response.getInt("num_tareas");
                                List<Tarea> tareasTablero = new ArrayList<>();
                                for (int i = 0; i < numTareas; i++) {
                                    Tarea tarea = new Tarea();
                                    tareasTablero.add(tarea);
                                }
                                tablero.setTareas(tareasTablero);
                                listaTableroLiveData.setValue(listTablero);
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
            queue.add(jsonObjectRequest);
        }
    }
}

package com.example.gestiontarea2023.Model;

import java.io.Serializable;
import java.util.List;
public class Tablero implements Serializable {

    private int id_tablero;
    private int id_usuario;
    private String nombre_tablero;
    private int estado;
    private String fecharegistro;
    private String fechaactualiza;
    private int id_usuarioregistro;
    private int id_usuarioactualiza;
    private List<Tarea> tareas;
    public List<Tarea> getTareas() {
        return tareas;
    }
    public void setTareas(List<Tarea> tareas) {
        this.tareas = tareas;
    }
    public Tablero() {

    }
    public Tablero(int id_tablero) {
        this.id_tablero = id_tablero;
    }
    public Tablero(int id_usuario, String nombre_tablero) {
        this.id_usuario = id_usuario;
        this.nombre_tablero = nombre_tablero;
    }
    public int getId_tablero() {
        return id_tablero;
    }
    public void setId_tablero(int id_tablero) {
        this.id_tablero = id_tablero;
    }
    public int getId_usuario() {
        return id_usuario;
    }
    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    public String getNombre_tablero() {
        return nombre_tablero;
    }
    public void setNombre_tablero(String nombre_tablero) {
        this.nombre_tablero = nombre_tablero;
    }

    public int getEstado() {
        return estado;
    }
    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getFecharegistro() {
        return fecharegistro;
    }

    public void setFecharegistro(String fecharegistro) {
        this.fecharegistro = fecharegistro;
    }

    public String getFechaactualiza() {
        return fechaactualiza;
    }

    public void setFechaactualiza(String fechaactualiza) {
        this.fechaactualiza = fechaactualiza;
    }

    public int getId_usuarioregistro() {
        return id_usuarioregistro;
    }

    public void setId_usuarioregistro(int id_usuarioregistro) {
        this.id_usuarioregistro = id_usuarioregistro;
    }
    public int getId_usuarioactualiza() {
        return id_usuarioactualiza;
    }

    public void setId_usuarioactualiza(int id_usuarioactualiza) {
        this.id_usuarioactualiza = id_usuarioactualiza;
    }
}

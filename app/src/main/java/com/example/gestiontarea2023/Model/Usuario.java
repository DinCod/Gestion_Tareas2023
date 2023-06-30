package com.example.gestiontarea2023.Model;
import java.io.Serializable;

public class Usuario implements Serializable{
    private int id_usuario;
    private String nombre;
    private String apellido_materno;
    private String apellido_paterno;
    private String dni;
    private String telefono;
    private String correo;
    private String clave;
    private String foto_perfil;
    private String estado;
    private String fecharegistro;
    private String fechaactualiza;
    private int id_usuarioregistro;
    private int id_usuarioactualiza;

    public Usuario(int id_usuario, String nombre, String apellido_materno, String dni,String apellido_paterno, String telefono, String correo, String clave, String foto_perfil, String estado, String fecharegistro, String fechaactualiza, int id_usuarioregistro, int id_usuarioactualiza) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.apellido_materno = apellido_materno;
        this.apellido_paterno = apellido_paterno;
        this.dni = dni;
        this.telefono = telefono;
        this.correo = correo;
        this.clave = clave;
        this.foto_perfil = foto_perfil;
        this.estado = estado;
        this.fecharegistro = fecharegistro;
        this.fechaactualiza = fechaactualiza;
        this.id_usuarioregistro = id_usuarioregistro;
        this.id_usuarioactualiza = id_usuarioactualiza;
    }

    public Usuario(String nombre, String apellido_materno, String apellido_paterno, String dni, String telefono, String correo, String clave) {
        this.nombre = nombre;
        this.apellido_materno = apellido_materno;
        this.apellido_paterno = apellido_paterno;
        this.dni = dni;
        this.telefono = telefono;
        this.correo = correo;
        this.clave = clave;
    }

    public Usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Usuario() {
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getFoto_perfil() {
        return foto_perfil;
    }

    public void setFoto_perfil(String foto_perfil) {
        this.foto_perfil = foto_perfil;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
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

package com.example.steinnxlabs.cineromeo30.modelo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by SteinnxLabs on 13-07-2017.
 */

public class Usuario {
    @SerializedName("idUsuario")
    private String id;
    @SerializedName("usuario")
    private String usuario;
    @SerializedName("contrasena")
    private String contrasena;
    @SerializedName("correo")
    private String correo;

    public Usuario(String usuario, String contrasena, String correo) {
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.correo = correo;
    }

    public Usuario(String id, String usuario, String contrasena, String correo) {
        this.id = id;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.correo = correo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}

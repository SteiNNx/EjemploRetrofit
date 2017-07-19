package com.example.steinnxlabs.cineromeo30.retrofit;

import com.example.steinnxlabs.cineromeo30.modelo.Usuario;
import com.google.gson.annotations.SerializedName;

/**
 * Created by SteinnxLabs on 13-07-2017.
 */

public class UsuarioResponse {
    @SerializedName("usuario")
    private Usuario usuario;

    @SerializedName("estado")
    private int estado;

    public UsuarioResponse(Usuario usuario, int estado) {
        this.usuario = usuario;
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}

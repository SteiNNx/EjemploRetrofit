package com.example.steinnxlabs.cineromeo30.modelo.SharedPreferences;

import com.example.steinnxlabs.cineromeo30.modelo.Pelicula;
import com.example.steinnxlabs.cineromeo30.retrofit.responses.UsuarioResponse;

/**
 * Created by SteinnxLabs on 21-07-2017.
 */

public class SharedPreferences {
    private static UsuarioResponse usuario;
    private static Pelicula pelicula;

    public SharedPreferences() {
    }

    public static UsuarioResponse getUsuario() {
        return usuario;
    }

    public static void setUsuario(UsuarioResponse usuario) {
        SharedPreferences.usuario = usuario;
    }

    public static Pelicula getPelicula() {
        return pelicula;
    }

    public static void setPelicula(Pelicula pelicula) {
        SharedPreferences.pelicula = pelicula;
    }
}

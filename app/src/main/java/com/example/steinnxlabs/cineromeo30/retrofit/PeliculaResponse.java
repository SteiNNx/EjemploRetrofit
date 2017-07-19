package com.example.steinnxlabs.cineromeo30.retrofit;

import com.example.steinnxlabs.cineromeo30.modelo.Pelicula;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by SteinnxLabs on 17-07-2017.
 */

public class PeliculaResponse {
    @SerializedName("peliculas")
    private List<Pelicula> peliculas;
    @SerializedName("estado")
    private int estado;

    public PeliculaResponse(List<Pelicula> peliculas, int estado) {
        this.peliculas = peliculas;
        this.estado = estado;
    }

    public List<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(List<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}

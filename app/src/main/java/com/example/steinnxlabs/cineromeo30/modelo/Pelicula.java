package com.example.steinnxlabs.cineromeo30.modelo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by SteinnxLabs on 16-07-2017.
 */

public class Pelicula {
    @SerializedName("idPelicula")
    private String idPelicula;
    @SerializedName("url_banner")
    private String url_banner;
    @SerializedName("url_avatar")
    private String url_avatar;
    @SerializedName("titulo")
    private String titulo;
    @SerializedName("resena")
    private String resena;
    @SerializedName("calificacion")
    private String calificacion;
    @SerializedName("duracion")
    private String duracion;
    @SerializedName("idioma")
    private String idioma;
    @SerializedName("horario")
    private String horario;

    public Pelicula() {
    }

    public String getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(String idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getUrl_banner() {
        return url_banner;
    }

    public void setUrl_banner(String url_banner) {
        this.url_banner = url_banner;
    }

    public String getUrl_avatar() {
        return url_avatar;
    }

    public void setUrl_avatar(String url_avatar) {
        this.url_avatar = url_avatar;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResena() {
        return resena;
    }

    public void setResena(String resena) {
        this.resena = resena;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
}

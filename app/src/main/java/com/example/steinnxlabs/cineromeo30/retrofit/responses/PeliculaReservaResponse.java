package com.example.steinnxlabs.cineromeo30.retrofit.responses;

import com.example.steinnxlabs.cineromeo30.modelo.PeliculaReserva;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by SteinnxLabs on 21-07-2017.
 */

public class PeliculaReservaResponse {
    @SerializedName("reservas")
    private List<PeliculaReserva> reservas;
    @SerializedName("estado")
    private int estado;

    public PeliculaReservaResponse(List<PeliculaReserva> reservas, int estado) {
        this.reservas = reservas;
        this.estado = estado;
    }

    public List<PeliculaReserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<PeliculaReserva> reservas) {
        this.reservas = reservas;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}

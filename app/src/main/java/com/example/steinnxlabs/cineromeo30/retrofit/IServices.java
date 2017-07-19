package com.example.steinnxlabs.cineromeo30.retrofit;

import com.example.steinnxlabs.cineromeo30.modelo.Usuario;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by SteinnxLabs on 13-07-2017.
 */

public interface IServices {
    //Web Services Login @Query ..login.php?usario=PARA_ENTRA1&contrasena=PARA_ENTRA2
    @GET("usuario/obtener_usuario_login.php")
    Call<UsuarioResponse> getLogin(@Query("usuario") String user,@Query("contrasena") String contra);
    //Web Services Registrarse @Body crea un json a partir de un objeto
    @POST("usuario/insertar_usuario.php")
    Call<Usuario> crearUsuario(@Body Usuario user);
    //Web Services Listar Peliculas
    @GET("pelicula/obtener_peliculas.php")
    Call<PeliculaResponse> getPeliculas();

}

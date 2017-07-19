package com.example.steinnxlabs.cineromeo30;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.steinnxlabs.cineromeo30.modelo.Usuario;
import com.example.steinnxlabs.cineromeo30.retrofit.IServices;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistrarseActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etUser, etCorreo, etContra1, etContra2;
    private Button btnCrear;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);
        showToolbar("Volver", true);

        etUser = (EditText) findViewById(R.id.etUsuarioRegistrarse);
        etCorreo = (EditText) findViewById(R.id.etCorreoRegistrarse);
        etContra1 = (EditText) findViewById(R.id.etContra1Registrarse);
        etContra2 = (EditText) findViewById(R.id.etContra2Registrarse);
        btnCrear = (Button) findViewById(R.id.btnRegistrarseRegistro);

        btnCrear.setOnClickListener(this);

    }

    public void showToolbar(String titulo, Boolean upButton) {
        Toolbar tol = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tol);
        getSupportActionBar().setTitle(titulo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnRegistrarseRegistro) {
            String mensajeError = "";
            if (etContra1.getText().toString().length() <= 0) {
                mensajeError += " Error Contraseña1 \n";
            } else if (etContra2.getText().toString().length() <= 0) {
                mensajeError += " Error Contraseña1 \n";
            } else if (!etContra1.getText().toString().equals(etContra2.getText().toString())) {
                mensajeError += " Contraseñas Distintas \n";
            } else if (etUser.getText().toString().length() <= 0) {
                mensajeError += " Error Usuario \n";
            } else if (etCorreo.getText().toString().length() <= 0) {
                mensajeError += " Error Correo \n";
            } else {
                crearUsuario();
            }
            Toast.makeText(this, mensajeError, Toast.LENGTH_SHORT).show();
        }

    }

    private void crearUsuario() {
        showDialog();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor).build();
        Gson gson = new GsonBuilder()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://kailalkalil.esy.es/webServices/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        IServices iServices = retrofit.create(IServices.class);
        Usuario usuario = new Usuario(etUser.getText().toString()
                , etContra1.getText().toString()
                , etCorreo.getText().toString());
        Call<Usuario> usuarioCall = iServices.crearUsuario(usuario);
        usuarioCall.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                dialog.dismiss();
                Toast.makeText(RegistrarseActivity.this, "Usuario Creado", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {

            }
        });
    }

    protected void showDialog() {
        if (dialog == null) {
            dialog = new ProgressDialog(this);
            dialog.setTitle("Creando");
            dialog.setMessage("Cargando...");
            dialog.setCancelable(false);
            //dialog.setIndeterminate(true);
        }
        dialog.show();
    }
}

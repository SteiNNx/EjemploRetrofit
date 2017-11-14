package com.example.steinnxlabs.cineromeo30;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.steinnxlabs.cineromeo30.modelo.SharedPreferences.SharedPreferences;
import com.example.steinnxlabs.cineromeo30.retrofit.IServices;
import com.example.steinnxlabs.cineromeo30.retrofit.responses.UsuarioResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etUsuario,etContrasena;
    private Button btnIngresar,btnRegistrarse;
    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsuario= (EditText) findViewById(R.id.etUsuarioLogin);
        etContrasena= (EditText) findViewById(R.id.etContraseñaLogin);
        btnIngresar= (Button) findViewById(R.id.btnIngresar);
        btnRegistrarse= (Button) findViewById(R.id.btnRegistrarse);

        btnIngresar.setOnClickListener(this);
        btnRegistrarse.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btnIngresar){
            login();
        }else if (v.getId()==R.id.btnRegistrarse){
            Intent i = new Intent(LoginActivity.this,RegistrarseActivity.class);
            startActivity(i);
        }
    }

    private void login() {
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
        IServices iServices =retrofit.create(IServices.class);

        Call<UsuarioResponse> responseCallback = iServices.getLogin(etUsuario.getText().toString(),etContrasena.getText().toString());
        responseCallback.enqueue(new Callback<UsuarioResponse>() {

            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                if (response.code() == 200) {
                    UsuarioResponse values = response.body();
                    if (values.getEstado()==1){
                        Toast.makeText(LoginActivity.this, "Nombre: " + values.getUsuario().getUsuario(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this,MenuPrincipalActivity.class);
                        SharedPreferences preferences= new SharedPreferences();
                        preferences.setUsuario(values);
                        startActivity(intent);
                        dialog.dismiss();
                    }else {
                        Toast.makeText(LoginActivity.this, "Incorrecto", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }

                }
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
    protected void showDialog(){
        if(dialog == null){
            dialog = new ProgressDialog(this);
            dialog.setTitle("Login");
            dialog.setMessage("Cargando...");
            dialog.setCancelable(false);
            //dialog.setIndeterminate(true);
        }
        dialog.show();
    }
}

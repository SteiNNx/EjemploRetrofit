package com.example.steinnxlabs.cineromeo30;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.steinnxlabs.cineromeo30.modelo.PeliculaReserva;
import com.example.steinnxlabs.cineromeo30.modelo.SharedPreferences.SharedPreferences;
import com.example.steinnxlabs.cineromeo30.retrofit.IServices;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetalleActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_banner, iv_mas, iv_menos;
    private TextView tv_titulo, tv_duracion, tv_idioma, tv_resena, tv_cantidad, tv_precio;
    private Button btn_reservar;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        //instacion componentes
        iv_banner = (ImageView) findViewById(R.id.iv_banner_detalle);
        iv_mas = (ImageView) findViewById(R.id.iv_mas);
        iv_menos = (ImageView) findViewById(R.id.iv_menos);
        tv_titulo = (TextView) findViewById(R.id.tv_titulo_detalle);
        tv_duracion = (TextView) findViewById(R.id.tv_duracion_detalle);
        tv_idioma = (TextView) findViewById(R.id.tv_idioma_detalle);
        tv_resena = (TextView) findViewById(R.id.tv_reseña_detalle);
        tv_cantidad = (TextView) findViewById(R.id.tv_cantidad_entradas);
        tv_precio = (TextView) findViewById(R.id.tv_precio_detalle);
        btn_reservar = (Button) findViewById(R.id.btn_reservar);
        showToolbar("Detalle Pelicula", true);
        //recuperos putextras del adapter
        Bundle extras = getIntent().getExtras();
        String titulo = extras.getString("titulo");
        String banner = extras.getString("banner");
        String duracion = extras.getString("duracion");
        String idioma = extras.getString("idioma");
        String resena = extras.getString("resena");
        String id = extras.getString("id");
        //seteo componenten con los datos recuperados
        Picasso.with(this).load(banner).into(iv_banner);
        tv_titulo.setText(titulo);
        tv_duracion.setText("Duracion: " + duracion);
        tv_idioma.setText("Idioma: " + idioma);
        tv_resena.setText(resena);

        iv_mas.setOnClickListener(this);
        iv_menos.setOnClickListener(this);
        btn_reservar.setOnClickListener(this);
    }

    //metodo para toolbar editar
    public void showToolbar(String titulo, Boolean upButton) {
        Toolbar tol = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tol);
        getSupportActionBar().setTitle(titulo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == iv_mas.getId()) {
            cantidad(1);
        } else if (v.getId() == iv_menos.getId()) {
            cantidad(-1);
        }else if (v.getId()==btn_reservar.getId()){
            realizarReserva();
        }
    }

    private void realizarReserva() {
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
        IServices services = retrofit.create(IServices.class);
        PeliculaReserva reserva = new PeliculaReserva();
        reserva.setIdUsuario(Integer.parseInt(SharedPreferences.getUsuario().getUsuario().getId()));
        reserva.setIdPelicula(SharedPreferences.getPelicula().getIdPelicula());
        reserva.setCantidadEntradas(Integer.parseInt(tv_cantidad.getText().toString()));
        Call<PeliculaReserva> responseCall = services.crearReserva(reserva);
        responseCall.enqueue(new Callback<PeliculaReserva>() {
            @Override
            public void onResponse(Call<PeliculaReserva> call, Response<PeliculaReserva> response) {
                Toast.makeText(DetalleActivity.this, "Reserva Creada", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<PeliculaReserva> call, Throwable t) {

            }
        });
    }

    private void cantidad(int cantidad) {
        if ((Integer.parseInt(tv_cantidad.getText().toString())+cantidad)>0&(Integer.parseInt(tv_cantidad.getText().toString())+cantidad)<11){
            tv_cantidad.setText(""+(Integer.parseInt(tv_cantidad.getText().toString())+cantidad));
        }
        tv_precio.setText("Precio: $"+(Integer.parseInt(tv_cantidad.getText().toString())*1500));
    }
}

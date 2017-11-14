package com.example.steinnxlabs.cineromeo30.modelo.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.steinnxlabs.cineromeo30.R;
import com.example.steinnxlabs.cineromeo30.modelo.PeliculaReserva;
import com.example.steinnxlabs.cineromeo30.retrofit.IServices;
import com.example.steinnxlabs.cineromeo30.retrofit.responses.PeliculaReservaResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by SteinnxLabs on 21-07-2017.
 */

public class ReservaAdapter extends RecyclerView.Adapter<ReservaAdapter.ReservaViewHolder> {

    private ArrayList<PeliculaReserva> dataSource;
    private Context context;

    public ReservaAdapter(Context context) {
        dataSource= new ArrayList<>();
        this.context = context;
    }
    @Override
    public ReservaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pelicula_reservas,parent,false);
        return new ReservaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReservaViewHolder holder, int position) {
        final PeliculaReserva reserva = dataSource.get(position);
        holder.tv_titulo.setText(reserva.getTitulo());
        holder.tv_duracion.setText("Duracion: "+reserva.getDuracion());
        holder.tv_cantidad_entradas.setText("Cantidad Entradas: "+reserva.getCantidadEntradas());
        holder.tv_horario.setText("Horario: "+reserva.getHorario());
        holder.tv_idioma.setText("Idioma: "+reserva.getIdioma());
        Picasso.with(context).load(reserva.getUrl_avatar()).into(holder.iv_avatar);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
                View view = layoutInflater.inflate(R.layout.dialog_eliminar,null);
                builder.setView(view);
                Button btnEliminar= (Button) view.findViewById(R.id.btn_eliminar_reserva);
                btnEliminar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Retrofit retrofit;
                        retrofit= new Retrofit.Builder()
                                .baseUrl("http://kailalkalil.esy.es/webServices/")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        IServices services = retrofit.create(IServices.class);
                        Call<PeliculaReservaResponse> peliculaResponseCall = services.eliminarReserva(reserva);
                        peliculaResponseCall.enqueue(new Callback<PeliculaReservaResponse>() {
                            @Override
                            public void onResponse(Call<PeliculaReservaResponse> call, Response<PeliculaReservaResponse> response) {
                                if (response.isSuccessful()){
                                    PeliculaReservaResponse response1 = response.body();
                                    Toast.makeText(context, "Eliminado", Toast.LENGTH_SHORT).show();
                                }else{
                                    Log.e("ERRORRRRR!!!!!!!!!!!!!!","onResponeseeeeeeeeeeee: "+response.errorBody());

                                }
                            }

                            @Override
                            public void onFailure(Call<PeliculaReservaResponse> call, Throwable t) {
                                Log.e("ERRORRRRR!!!!!!!!!!!!!!","onFailureeeeeeeeeee: "+t.getMessage());

                            }
                        });
                    }
                });
                builder.create().show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public void adiccionarDatos(List<PeliculaReserva> peliculaReservas) {
        if(peliculaReservas!=null){
            dataSource.addAll(peliculaReservas);
            notifyDataSetChanged();
        }
    }

    public class ReservaViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv_avatar;
        private TextView tv_titulo,tv_cantidad_entradas,tv_duracion,tv_horario,tv_idioma;

        public ReservaViewHolder(View itemView) {
            super(itemView);
            iv_avatar= (ImageView) itemView.findViewById(R.id.iv_avatar_item_reserva);
            tv_titulo = (TextView) itemView.findViewById(R.id.tv_titulo_item_reserva);
            tv_cantidad_entradas = (TextView) itemView.findViewById(R.id.tv_cantidad_item_reserva);
            tv_duracion = (TextView) itemView.findViewById(R.id.tv_duracion_item_reserva);
            tv_idioma = (TextView) itemView.findViewById(R.id.tv_idioma_item_reservas);
            tv_horario = (TextView) itemView.findViewById(R.id.tv_horario_item_reserva);
        }
    }
}

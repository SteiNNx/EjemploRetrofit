package com.example.steinnxlabs.cineromeo30.modelo.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.steinnxlabs.cineromeo30.DetalleActivity;
import com.example.steinnxlabs.cineromeo30.R;
import com.example.steinnxlabs.cineromeo30.modelo.Pelicula;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SteinnxLabs on 17-07-2017.
 */

public class PeliculaAdapter extends RecyclerView.Adapter<PeliculaAdapter.PeliculaViewHolder> {

    private ArrayList<Pelicula> dataSource;
    private Context context;

    public PeliculaAdapter(Context context) {
        dataSource= new ArrayList<>();
        this.context = context;
    }

    @Override
    public PeliculaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pelicula_cartelera,parent,false);
        return new PeliculaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PeliculaViewHolder holder, int position) {
        final Pelicula pelicula = dataSource.get(position);
        Picasso.with(context).load(pelicula.getUrl_avatar()).into(holder.iv_avatar);
        holder.tv_titulo.setText(pelicula.getTitulo());
        holder.tv_calificacion.setText(pelicula.getCalificacion());
        holder.tv_duracion.setText("Duracion: "+pelicula.getDuracion());
        holder.tv_idioma.setText("Idioma: "+pelicula.getIdioma());
        holder.tv_horario.setText(pelicula.getHorario());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetalleActivity.class);
                intent.putExtra("banner",pelicula.getUrl_banner());
                intent.putExtra("titulo",pelicula.getTitulo());
                intent.putExtra("duracion",pelicula.getDuracion());
                intent.putExtra("idioma",pelicula.getIdioma());
                intent.putExtra("resena",pelicula.getResena());
                intent.putExtra("id",pelicula.getIdPelicula());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public void adiccionarPeliculas(List<Pelicula> listadoPeliculas) {
        dataSource.addAll(listadoPeliculas);
        notifyDataSetChanged();
    }

    public class PeliculaViewHolder extends RecyclerView.ViewHolder{

        private ImageView iv_avatar;
        private TextView tv_titulo,tv_calificacion,tv_duracion,tv_idioma,tv_horario;

        public PeliculaViewHolder(View itemView) {
            super(itemView);
            tv_titulo= (TextView) itemView.findViewById(R.id.tv_titulo_item_cartelera);
            tv_calificacion= (TextView) itemView.findViewById(R.id.tv_calificacion_item_cartelera);
            tv_duracion= (TextView) itemView.findViewById(R.id.tv_duracion_item_cartelera);
            tv_idioma= (TextView) itemView.findViewById(R.id.tv_idioma_item_cartelera);
            tv_horario= (TextView) itemView.findViewById(R.id.tv_horario_item_cartelera);
            iv_avatar= (ImageView) itemView.findViewById(R.id.iv_avatar_item_cartelera);
        }
    }
}

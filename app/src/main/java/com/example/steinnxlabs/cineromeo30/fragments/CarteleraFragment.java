package com.example.steinnxlabs.cineromeo30.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.steinnxlabs.cineromeo30.R;
import com.example.steinnxlabs.cineromeo30.modelo.Pelicula;
import com.example.steinnxlabs.cineromeo30.modelo.adapters.PeliculaAdapter;
import com.example.steinnxlabs.cineromeo30.retrofit.IServices;
import com.example.steinnxlabs.cineromeo30.retrofit.PeliculaResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CarteleraFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class CarteleraFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    //Componentes Creados y declaro Retrofit
    private TextView tv_cartelera;
    private RecyclerView rv_listarPeliculas;
    private PeliculaAdapter peliculaAdapter;
    private Retrofit retrofit;

    public CarteleraFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_cartelera, container, false);
        //Instancion Componentes
        tv_cartelera= (TextView) view.findViewById(R.id.tv_cartelera_fragment);
        rv_listarPeliculas = (RecyclerView) view.findViewById(R.id.rv_frag_cartelera);
        //instacion adapter
        peliculaAdapter = new PeliculaAdapter(getContext());
        //le doy forma al reciclerView
        rv_listarPeliculas.setAdapter(peliculaAdapter);
        rv_listarPeliculas.setHasFixedSize(true);
        final GridLayoutManager manager = new GridLayoutManager(container.getContext(),1);
        rv_listarPeliculas.setLayoutManager(manager);
        //instancion retrofit para usarlo
        retrofit= new Retrofit.Builder()
                .baseUrl("http://kailalkalil.esy.es/webServices/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //Lleno reciclerView con este metodo
        llenarRecicler();
        return view;
    }

    private void llenarRecicler() {
        //se instancia el puente con el webservices
        IServices services = retrofit.create(IServices.class);
        //Realizo la llamada y obtengo los datos json y hago la inyeccion y se los paso a peliculasRespone
        Call<PeliculaResponse> peliculaResponseCall = services.getPeliculas();
        peliculaResponseCall.enqueue(new Callback<PeliculaResponse>() {
            @Override
            public void onResponse(Call<PeliculaResponse> call, Response<PeliculaResponse> response) {
                if (response.isSuccessful()){
                    PeliculaResponse peliculaResponse = response.body();
                    List<Pelicula> listadoPeliculas = peliculaResponse.getPeliculas();
                    peliculaAdapter.adiccionarPeliculas(listadoPeliculas);
                }else {
                    Log.e("ERRORRRRR!!!!!!!!!!!!!!","onResponeseeeeeeeeeeee: "+response.errorBody());
                }
            }
            @Override
            public void onFailure(Call<PeliculaResponse> call, Throwable t) {
                Log.e("ERRORRRRR!!!!!!!!!!!!!!","onFailureeeeeeeeeee: "+t.getMessage());
            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

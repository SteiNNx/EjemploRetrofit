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

import com.example.steinnxlabs.cineromeo30.R;
import com.example.steinnxlabs.cineromeo30.modelo.PeliculaReserva;
import com.example.steinnxlabs.cineromeo30.modelo.SharedPreferences.SharedPreferences;
import com.example.steinnxlabs.cineromeo30.modelo.adapters.ReservaAdapter;
import com.example.steinnxlabs.cineromeo30.retrofit.IServices;
import com.example.steinnxlabs.cineromeo30.retrofit.responses.PeliculaReservaResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ReservasFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class ReservasFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private RecyclerView rv_listarReservas;
    private ReservaAdapter reservaAdapter;
    private Retrofit retrofit;

    public ReservasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reservas, container, false);
        rv_listarReservas = (RecyclerView) view.findViewById(R.id.rv_frag_reservas);
        reservaAdapter = new ReservaAdapter(getContext());
        rv_listarReservas.setAdapter(reservaAdapter);
        rv_listarReservas.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(container.getContext(), 1);
        rv_listarReservas.setLayoutManager(layoutManager);
        retrofit = new Retrofit.Builder()
                .baseUrl("http://kailalkalil.esy.es/webServices/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        llenarReservasRecyclerView();
        return view;
    }

    private void llenarReservasRecyclerView() {
        IServices services = retrofit.create(IServices.class);
        Call<PeliculaReservaResponse> reservaResponseCall = services.getReservas(SharedPreferences.getUsuario().getUsuario().getId());
        reservaResponseCall.enqueue(new Callback<PeliculaReservaResponse>() {
            @Override
            public void onResponse(Call<PeliculaReservaResponse> call, Response<PeliculaReservaResponse> response) {
                if (response.isSuccessful()) {
                    PeliculaReservaResponse reservaResponse = response.body();
                    List<PeliculaReserva> peliculaReservas = reservaResponse.getReservas();
                    reservaAdapter.adiccionarDatos(peliculaReservas);
                } else {
                    Log.e("ERRORRRRR!!!!!!!!!!!!!!", "onResponeseeeeeeeeeeee: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PeliculaReservaResponse> call, Throwable t) {
                Log.e("ERRORRRRR!!!!!!!!!!!!!!", "onFailureeeeeeeeeee: " + t.getMessage());

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

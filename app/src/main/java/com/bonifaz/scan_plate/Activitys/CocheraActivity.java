package com.bonifaz.scan_plate.Activitys;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bonifaz.scan_plate.Models.CocheraResponse;
import com.bonifaz.scan_plate.Models.MensajeResponse;
import com.bonifaz.scan_plate.R;
import com.bonifaz.scan_plate.Service.ApiService;
import com.bonifaz.scan_plate.Utils.Config;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CocheraActivity extends AppCompatActivity {


    private TextView tvNombreEstacionamiento;
    private TextView tvEspaciosTotales;
    private TextView tvEspaciosDisponibles;
    private TextView tvInfoEspacio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cochera);

        tvNombreEstacionamiento = findViewById(R.id.tvTitulo);
        tvEspaciosTotales = findViewById(R.id.tvEspaciosTotales);
        tvEspaciosDisponibles = findViewById(R.id.tvEspaciosDisponibles);
        tvInfoEspacio = findViewById(R.id.tvInfoEspacio);

        obtenerDatosCochera();

    }

    private void obtenerDatosCochera() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<List<CocheraResponse>> call = apiService.obtenerDatosCochera();


        call.enqueue(new Callback<List<CocheraResponse>>() {
            @Override
            public void onResponse(Call<List<CocheraResponse>> call, Response<List<CocheraResponse>> response) {
                if (response.isSuccessful()) {
                    List<CocheraResponse> cocheraResponses = response.body();
                    if (cocheraResponses != null && !cocheraResponses.isEmpty()) {
                        CocheraResponse cocheraResponse = cocheraResponses.get(0);
                        tvNombreEstacionamiento.setText(cocheraResponse.getNOM_ESTACIONAMIENTO());
                        tvEspaciosTotales.setText(String.valueOf(cocheraResponse.getCANTIDAD_TOTAL()));
                        tvEspaciosDisponibles.setText(String.valueOf(cocheraResponse.getCANTIDAD_DISPONIBLE()));

                        int espaciosDisponibles = cocheraResponse.getCANTIDAD_DISPONIBLE();
                        String mensaje;
                        int colorFondo;

                        if (espaciosDisponibles < 10) {
                            mensaje = "QUEDAN POCOS ESPACIOS";
                            colorFondo = getResources().getColor(R.color.colorRojo); // Rojo
                        } else if (espaciosDisponibles >= 11 && espaciosDisponibles <= 50) {
                            mensaje = "ESPACIOS BIEN";
                            colorFondo = getResources().getColor(R.color.colorNaranja); // Naranja
                        } else {
                            mensaje = "COCHERA MUY LIBRE";
                            colorFondo = getResources().getColor(R.color.colorVerde); // Verde
                        }

                        tvInfoEspacio.setText(mensaje);
                        tvInfoEspacio.setBackgroundColor(colorFondo);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<CocheraResponse>> call, Throwable t) {
                // Manejo de errores
            }
        });
    }

}
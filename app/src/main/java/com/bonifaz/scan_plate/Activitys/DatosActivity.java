package com.bonifaz.scan_plate.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bonifaz.scan_plate.Models.MensajeResponse;
import com.bonifaz.scan_plate.R;
import com.bonifaz.scan_plate.Service.ApiService;
import com.bonifaz.scan_plate.Utils.Config;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DatosActivity extends AppCompatActivity {

    private TextView textViewDni, textViewCodigo, textViewApellidos, textViewNombres, textViewNomTipoVehiculo, textViewPlaca;

    private Button buttonRegistrarPlaca, btnRegistrarSalida;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        textViewDni = findViewById(R.id.textViewDni);
        textViewCodigo = findViewById(R.id.textViewCodigo);
        textViewApellidos = findViewById(R.id.textViewApellidos);
        textViewNombres = findViewById(R.id.textViewNombres);
        textViewNomTipoVehiculo = findViewById(R.id.textViewNomTipoVehiculo);
        textViewPlaca = findViewById(R.id.textViewPlaca);
        buttonRegistrarPlaca = findViewById(R.id.buttonRegistrarPlaca);
        btnRegistrarSalida = findViewById(R.id.btnRegistrarSalida);


        // Obtener los datos del intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String dni = extras.getString("dni");
            String codigo = extras.getString("codigo");
            String appaterno = extras.getString("appaterno");
            String apmaterno = extras.getString("apmaterno");
            String nombres = extras.getString("nombres");
            String nomTipoVehiculo = extras.getString("nomTipoVehiculo");
            String placa = extras.getString("placa");

            // Concatenar apellidos
            String apellidos = appaterno + " " + apmaterno;

            // Setear los valores concatenados
            textViewDni.setText(dni);
            textViewCodigo.setText(codigo);
            textViewApellidos.setText(apellidos);
            textViewNombres.setText(nombres);
            textViewNomTipoVehiculo.setText(nomTipoVehiculo);
            textViewPlaca.setText(placa);


            // Configurar el botón para registrar la placa
            buttonRegistrarPlaca.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    registrarPlaca(placa);
                }
            });

            btnRegistrarSalida.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    registrarSalidaPlaca(placa); // Pasa la placa a registrar salida
                }
            });
        }
    }


    private void registrarPlaca(String placa) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        Call<MensajeResponse> call = apiService.insertarPlacaEscaneada(placa);
        call.enqueue(new Callback<MensajeResponse>() {
            @Override
            public void onResponse(Call<MensajeResponse> call, Response<MensajeResponse> response) {
                Intent intent = new Intent(DatosActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                if (response.isSuccessful()) {
                    Toast.makeText(DatosActivity.this, "Placa registrada exitosamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DatosActivity.this, "Error al registrar la placa", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MensajeResponse> call, Throwable t) {
                Toast.makeText(DatosActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void registrarSalidaPlaca(String placa) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<MensajeResponse> call = apiService.salidaPlaca(placa);

        call.enqueue(new Callback<MensajeResponse>() {
            @Override
            public void onResponse(Call<MensajeResponse> call, Response<MensajeResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(DatosActivity.this, response.body().getText(), Toast.LENGTH_SHORT).show();
                    // Redirigir a MainActivity
                    Intent intent = new Intent(DatosActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    if (response.isSuccessful()) {
                        Toast.makeText(DatosActivity.this, "Salida de Placa exitosamente", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DatosActivity.this, "Error al darle salida a la placa", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(DatosActivity.this, "Error al registrar la salida", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MensajeResponse> call, Throwable t) {
                Toast.makeText(DatosActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
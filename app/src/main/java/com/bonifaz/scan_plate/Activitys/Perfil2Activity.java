package com.bonifaz.scan_plate.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.bonifaz.scan_plate.R;

public class Perfil2Activity extends AppCompatActivity {

    private EditText textViewDocumento;
    private EditText textViewCodigo;
    private EditText textViewNombres;
    private EditText textViewAppaterno;
    private EditText textViewNomTipoVehiculo;
    private EditText textViewPlaca;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil2);

        textViewDocumento = findViewById(R.id.textViewDocumento);
        textViewCodigo = findViewById(R.id.textViewCodigo);
        textViewNombres = findViewById(R.id.textViewNombres);
        textViewAppaterno = findViewById(R.id.textViewAppaterno);
        textViewNomTipoVehiculo = findViewById(R.id.textViewNomTipoVehiculo);
        textViewPlaca = findViewById(R.id.textViewPlaca);


        // Recuperar datos del usuario desde SharedPreferences
        SharedPreferences preferences = getSharedPreferences("session", MODE_PRIVATE);
        String documento = preferences.getString("documento", "");
        String codigo = preferences.getString("codigo", "");
        String nombres = preferences.getString("nombres", "");
        String appaterno = preferences.getString("appaterno", "");
        String apmaterno = preferences.getString("apmaterno", "");
        String placa = preferences.getString("placa", "");
        String vehiculo = preferences.getString("vehiculo", "");
        String nombrePrivilegio = preferences.getString("nombrePrivilegio", "");

        // Mostrar los datos del usuario
        textViewDocumento.setText(documento);
        textViewCodigo.setText(codigo);
        textViewNombres.setText(nombres);
        textViewAppaterno.setText(appaterno + " " + apmaterno);
        textViewNomTipoVehiculo.setText(vehiculo);
        textViewPlaca.setText(placa);
    }
}
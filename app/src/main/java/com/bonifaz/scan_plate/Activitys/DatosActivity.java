package com.bonifaz.scan_plate.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.bonifaz.scan_plate.R;

public class DatosActivity extends AppCompatActivity {

    private TextView textViewDni, textViewCodigo, textViewApellidos, textViewNombres, textViewNomTipoVehiculo, textViewPlaca;


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
        }
    }
}
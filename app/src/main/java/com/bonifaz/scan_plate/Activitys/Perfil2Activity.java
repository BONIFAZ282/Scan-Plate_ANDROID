package com.bonifaz.scan_plate.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar; // Importar la Toolbar correcta

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.bonifaz.scan_plate.R;

public class Perfil2Activity extends AppCompatActivity {

    private TextView textViewDocumento;
    private TextView textViewCodigo;
    private TextView textViewNombres;
    private TextView textViewAppaterno;
    private TextView textViewNomTipoVehiculo;
    private TextView textViewPlaca;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil2);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // Cierra la actividad actual y regresa a la anterior
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
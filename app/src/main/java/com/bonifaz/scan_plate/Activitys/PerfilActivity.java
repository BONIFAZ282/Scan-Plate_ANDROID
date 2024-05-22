package com.bonifaz.scan_plate.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.bonifaz.scan_plate.R;

public class PerfilActivity extends AppCompatActivity {

    private TextView textViewDocumento;
    private TextView textViewCodigo;
    private TextView textViewNombres;
    private TextView textViewNombrePrivilegio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        textViewDocumento = findViewById(R.id.textViewDocumento);
        textViewCodigo = findViewById(R.id.textViewCodigo);
        textViewNombres = findViewById(R.id.textViewNombres);

        textViewNombrePrivilegio = findViewById(R.id.textViewNombrePrivilegio);

        // Recuperar datos del usuario desde SharedPreferences
        SharedPreferences preferences = getSharedPreferences("session", MODE_PRIVATE);
        String documento = preferences.getString("documento", "");
        String codigo = preferences.getString("codigo", "");
        String nombres = preferences.getString("nombres", "");
        String nombrePrivilegio = preferences.getString("nombrePrivilegio", "");

        // Mostrar los datos del usuario
        textViewDocumento.setText("DOCUMENTO: " + documento);
        textViewCodigo.setText("CODIGO: " + codigo);
        textViewNombres.setText("NOMBRES: " + nombres);
        textViewNombrePrivilegio.setText("NOMBRE PRIVILEGIO: " + nombrePrivilegio);
    }
}
package com.bonifaz.scan_plate.Activitys;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bonifaz.scan_plate.R;

public class Menu2Activity extends AppCompatActivity {

    Button BtnCochera, BtnPerfil, BtnAcercaDe, BtnSalir;

    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu2);


        BtnCochera = findViewById(R.id.BtnCochera);
        BtnPerfil = findViewById(R.id.BtnPerfil);
        BtnAcercaDe = findViewById(R.id.BtnAcercaDe);

        BtnSalir = findViewById(R.id.BtnSalir);
        dialog = new Dialog(this);

        BtnCochera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu2Activity.this, CocheraActivity.class));
            }
        });

        BtnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu2Activity.this, PerfilActivity.class));
            }
        });

        BtnAcercaDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MostrarDialog();
            }
        });

        BtnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void MostrarDialog(){
        Button BtnEntendido;

        dialog.setContentView(R.layout.acerca_de);

        BtnEntendido = dialog.findViewById(R.id.BtnEntendido);

        BtnEntendido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
    }

    }

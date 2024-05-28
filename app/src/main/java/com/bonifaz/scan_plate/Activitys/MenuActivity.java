package com.bonifaz.scan_plate.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.bonifaz.scan_plate.R;

public class MenuActivity extends AppCompatActivity {

    Button BtnReconocer, BtnPerfil, BtnAcercaDe, BtnSalir;

    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        BtnReconocer = findViewById(R.id.BtnReconocer);
        BtnPerfil = findViewById(R.id.BtnPerfil);
        BtnAcercaDe = findViewById(R.id.BtnAcercaDe);

        BtnSalir = findViewById(R.id.BtnSalir);
        dialog = new Dialog(this);

        BtnReconocer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, MainActivity.class));
            }
        });

        BtnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, PerfilActivity.class));
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
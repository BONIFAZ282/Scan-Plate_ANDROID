package com.bonifaz.scan_plate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Environment;
import android.os.Handler;
import android.os.StatFs;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private TextView textViewAvailableSpace;
    private TextView textViewTotalSpace;
    private TextView textViewTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewAvailableSpace = findViewById(R.id.textViewAvailableSpace);
        textViewTotalSpace = findViewById(R.id.textViewTotalSpace);
        textViewTime = findViewById(R.id.textViewTime);
        Button buttonRefresh = findViewById(R.id.buttonRefresh);

        // Llama a la función para actualizar el espacio disponible, total y la hora en tiempo real cuando se presione el botón "Actualizar"
        buttonRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateStorageSpace();
            }
        });

        // Actualiza el espacio disponible, total y la hora en tiempo real cuando se inicia la actividad
        updateStorageSpace();

        // Actualiza la hora en tiempo real cada segundo
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                updateTime();
                handler.postDelayed(this, 1000);
            }
        });
    }

    // Función para actualizar el espacio disponible y total
    private void updateStorageSpace() {
        // Obtén la ruta de almacenamiento externo
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());

        // Calcula el espacio disponible y total
        long blockSize = stat.getBlockSizeLong();
        long availableBlocks = stat.getAvailableBlocksLong();
        long totalBlocks = stat.getBlockCountLong();

        // Convierte bytes a megabytes
        long availableSpaceMB = availableBlocks * blockSize / (1024 * 1024);
        long totalSpaceMB = totalBlocks * blockSize / (1024 * 1024);

        // Actualiza los TextViews con la información calculada
        textViewAvailableSpace.setText(String.valueOf(availableSpaceMB) + " MB");
        textViewTotalSpace.setText(String.valueOf(totalSpaceMB) + " MB");
    }

    // Función para actualizar la hora en tiempo real
    private void updateTime() {
        // Obtiene la hora actual
        long currentTimeMillis = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        String currentTime = sdf.format(new Date(currentTimeMillis));

        // Actualiza el TextView con la hora actual
        textViewTime.setText(currentTime);
    }
}
package com.bonifaz.scan_plate.Activitys;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputFilter;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;

import com.bonifaz.scan_plate.Models.PlacaResponse;
import com.bonifaz.scan_plate.R;
import com.bonifaz.scan_plate.Service.ApiService;
import com.bonifaz.scan_plate.Utils.Config;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Button ReconocerTexto;
    private Button VerDatos;
    private ImageView imagen;
    private EditText editTextPlaca;

    private Uri uri = null;

    private ProgressDialog progressDialog;

    private TextRecognizer textRecognizer;

    private ApiService apiService;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ReconocerTexto = findViewById(R.id.ReconocerTexto);
        imagen = findViewById(R.id.imagen);
        editTextPlaca = findViewById(R.id.editTextPlaca);
        VerDatos = findViewById(R.id.VerDatos);

        // Establecer el filtro para limitar la longitud del texto a 7 caracteres
        InputFilter[] filters = new InputFilter[]{new InputFilter.LengthFilter(7)};
        editTextPlaca.setFilters(filters);

        // Configurar Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

        VerDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String placa = editTextPlaca.getText().toString().trim();
                if (placa.length() == 7) {
                    // Realizar la acción correspondiente
                    buscarPlaca();
                } else {
                    Toast.makeText(MainActivity.this, "Ingrese una placa válida de 7 caracteres", Toast.LENGTH_SHORT).show();
                }
            }
        });




        // Vincular la barra de herramientas con la actividad
        Toolbar toolbar = findViewById(R.id.toolbar_custom);
        setSupportActionBar(toolbar);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Espero Porfavor");
        progressDialog.setCanceledOnTouchOutside(false);

        textRecognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);

        ReconocerTexto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uri == null) {
                    Toast.makeText(MainActivity.this, "Porfavor, Seleccione una imagen", Toast.LENGTH_SHORT).show();
                }
                else {
                    reconocerTextDeImagen();
                }
            }
        });

    }


    private void buscarPlaca() {
        String placa = editTextPlaca.getText().toString().trim();

        Call<List<PlacaResponse>> call = apiService.buscarPlaca(placa);
        call.enqueue(new Callback<List<PlacaResponse>>() {
            @Override
            public void onResponse(Call<List<PlacaResponse>> call, Response<List<PlacaResponse>> response) {
                if (response.isSuccessful()) {
                    List<PlacaResponse> placaResponseList = response.body();
                    if (placaResponseList != null && !placaResponseList.isEmpty()) {
                        PlacaResponse placaResponse = placaResponseList.get(0);
                        if (placaResponse.getMensaje() == null) {
                            // Pasar los datos a DetallePlacaActivity
                            Intent intent = new Intent(MainActivity.this, DatosActivity.class);
                            intent.putExtra("dni", placaResponse.getDni());
                            intent.putExtra("codigo", placaResponse.getCodigo());
                            intent.putExtra("appaterno", placaResponse.getAppaterno());
                            intent.putExtra("apmaterno", placaResponse.getApmaterno());
                            intent.putExtra("nombres", placaResponse.getNombres());
                            intent.putExtra("nomTipoVehiculo", placaResponse.getNomTipoVehiculo());
                            intent.putExtra("placa", placaResponse.getPlaca());
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, placaResponse.getMensaje(), Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Error en la respuesta del servidor", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<PlacaResponse>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error en la solicitud", Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void reconocerTextDeImagen(){
        progressDialog.setMessage("Preparando Imagen");
        progressDialog.dismiss();

        try {
            InputImage inputImage = InputImage.fromFilePath(this, uri);
            progressDialog.setMessage("Reconociendo Texto");
            Task<Text> textTask = textRecognizer.process(inputImage)
                    .addOnSuccessListener(new OnSuccessListener<Text>() {
                        @Override
                        public void onSuccess(Text text) {
                            progressDialog.dismiss();
                            String texto = text.getText();
                            editTextPlaca.setText(texto);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(MainActivity.this, "No se pudo reconocer el texto, debido a: "+e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
        } catch (IOException e) {
            progressDialog.dismiss();
            Toast.makeText(MainActivity.this, "Error al cargar la imagen: "+e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }

    private void AbrirGaleria(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        galeriaARL.launch(intent);
    }

    private void AbrirCamara(){
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "Titulo");
        values.put(MediaStore.Images.Media.DESCRIPTION, "Descripcion");

        uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        camaraARL.launch(intent);

    }

    private ActivityResultLauncher<Intent> galeriaARL = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        uri = data.getData();
                        imagen.setImageURI((uri));
                        editTextPlaca.setText("");
                    } else {
                        Toast.makeText(MainActivity.this, "Cancelado por el usuario", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );

    private ActivityResultLauncher<Intent> camaraARL = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        imagen.setImageURI(uri);
                        editTextPlaca.setText("");
                    } else {
                        Toast.makeText(MainActivity.this, "Cancelado por el usuario", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mi_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.Menu_abrir_galeria){
            AbrirGaleria();
            // Toast.makeText(this, "Abrir Galeria", Toast.LENGTH_SHORT).show();
        }
        if (item.getItemId() == R.id.Menu_abrir_camara){
            AbrirCamara();
            // Toast.makeText(this, "Abrir Cámara", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
package com.bonifaz.scan_plate.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.Toolbar; // Importar la Toolbar correcta

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.bonifaz.scan_plate.Models.Categoria;
import com.bonifaz.scan_plate.Models.MensajeResponse;
import com.bonifaz.scan_plate.Models.TipoVehiculo;
import com.bonifaz.scan_plate.Models.UsuarioRegister;
import com.bonifaz.scan_plate.R;
import com.bonifaz.scan_plate.Service.ApiService;
import com.bonifaz.scan_plate.Utils.Config;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RegistroActivity extends AppCompatActivity {

    private EditText txtnombres, txtappaterno, txtapmaterno, txtdni;
    private EditText txtcodigo, txtplaca, txtcontrasenia;
    private Spinner txtcategoria, txtvehiculo;
    private Button btnRegistrar;

    private List<Categoria> categorias;
    private List<TipoVehiculo> tiposVehiculo;

    private ArrayAdapter<Categoria> categoriaAdapter;
    private ArrayAdapter<TipoVehiculo> vehiculoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        txtnombres = findViewById(R.id.txtnombres);
        txtappaterno = findViewById(R.id.txtappaterno);
        txtapmaterno = findViewById(R.id.txtapmaterno);
        txtdni = findViewById(R.id.txtdni);
        txtcodigo = findViewById(R.id.txtcodigo);
        txtplaca = findViewById(R.id.txtplaca);
        txtcontrasenia = findViewById(R.id.txtcontrasenia);
        txtcategoria = findViewById(R.id.txtcategoria);
        txtvehiculo = findViewById(R.id.txtvehiculo);
        btnRegistrar = findViewById(R.id.btnRegistrar);


        InputFilter[] filtersdni = new InputFilter[]{new InputFilter.LengthFilter(8)};
        txtdni.setFilters(filtersdni);
        InputFilter[] filterscod = new InputFilter[]{new InputFilter.LengthFilter(9)};
        txtcodigo.setFilters(filterscod);
        InputFilter[] filtersplaca = new InputFilter[]{new InputFilter.LengthFilter(7)};
        txtplaca.setFilters(filtersplaca);

        // Convertir texto de txtPlaca a mayúsculas
        txtplaca.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                txtplaca.removeTextChangedListener(this);
                String text = s.toString().toUpperCase();
                txtplaca.setText(text);
                txtplaca.setSelection(text.length());
                txtplaca.addTextChangedListener(this);
            }
        });

        categoriaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        categoriaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        txtcategoria.setAdapter(categoriaAdapter);

        vehiculoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        vehiculoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        txtvehiculo.setAdapter(vehiculoAdapter);

        llenarCategorias();
        llenarTiposVehiculo();

        btnRegistrar.setOnClickListener(v -> registrarUsuario());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // Cierra la actividad actual y regresa a la anterior
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void llenarCategorias() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<List<Categoria>> callCategorias = apiService.getCategorias();
        callCategorias.enqueue(new Callback<List<Categoria>>() {
            @Override
            public void onResponse(Call<List<Categoria>> call, Response<List<Categoria>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    categorias = response.body();
                    List<String> nombresCategorias = new ArrayList<>();
                    for (Categoria categoria : categorias) {
                        nombresCategorias.add(categoria.getNombre());
                    }
                    ArrayAdapter<String> categoriaAdapter = new ArrayAdapter<>(RegistroActivity.this, android.R.layout.simple_spinner_item, nombresCategorias);
                    categoriaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    txtcategoria.setAdapter(categoriaAdapter);
                } else {
                    Toast.makeText(RegistroActivity.this, "Error al obtener las Categorias. Por favor, inténtelo más tarde.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Categoria>> call, Throwable t) {
                Toast.makeText(RegistroActivity.this, "Error de conexión. Por favor, verifique su conexión a Internet e inténtelo de nuevo.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void llenarTiposVehiculo() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<List<TipoVehiculo>> callTiposVehiculo = apiService.getTiposVehiculo();
        callTiposVehiculo.enqueue(new Callback<List<TipoVehiculo>>() {
            @Override
            public void onResponse(Call<List<TipoVehiculo>> call, Response<List<TipoVehiculo>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    tiposVehiculo = response.body();
                    List<String> nombresTiposVehiculo = new ArrayList<>();
                    for (TipoVehiculo tipoVehiculo : tiposVehiculo) {
                        nombresTiposVehiculo.add(tipoVehiculo.getNombre());
                    }
                    ArrayAdapter<String> vehiculoAdapter = new ArrayAdapter<>(RegistroActivity.this, android.R.layout.simple_spinner_item, nombresTiposVehiculo);
                    vehiculoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    txtvehiculo.setAdapter(vehiculoAdapter);
                } else {
                    Toast.makeText(RegistroActivity.this, "Error al obtener los tipos de vehículo. Por favor, inténtelo más tarde.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<TipoVehiculo>> call, Throwable t) {
                Toast.makeText(RegistroActivity.this, "Error de conexión. Por favor, verifique su conexión a Internet e inténtelo de nuevo.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void registrarUsuario(){
        String nombres = txtnombres.getText().toString().trim();
        String appaterno = txtappaterno.getText().toString().trim();
        String apmaterno = txtapmaterno.getText().toString().trim();
        String dni = txtdni.getText().toString().trim();
        String codigo = txtcodigo.getText().toString().trim();
        String placa = txtplaca.getText().toString().trim();
        String contrasenia = txtcontrasenia.getText().toString().trim();

        if (nombres.isEmpty() || appaterno.isEmpty() || apmaterno.isEmpty() || dni.isEmpty() ||
                codigo.isEmpty()  || placa.isEmpty() || contrasenia.isEmpty()) {
            Toast.makeText(this, "Todos los campos deben estar completos", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!dni.matches("\\d{8}")) {
            Toast.makeText(this, "El documento debe de tener 8 caracteres", Toast.LENGTH_SHORT).show();
            return;
        }


        if (nombres.matches(".*\\d.*") || appaterno.matches(".*\\d.*") || apmaterno.matches(".*\\d.*")) {
            Toast.makeText(this, "Los nombres y apellidos no deben contener números", Toast.LENGTH_SHORT).show();
            return;
        }


        int idCategoria = categorias.get(txtcategoria.getSelectedItemPosition()).getIdCategoria();

        // Obtener ID del tipo de vehículo seleccionado
        int idTipoVehiculo = tiposVehiculo.get(txtvehiculo.getSelectedItemPosition()).getIdTipoVehiculo();


        UsuarioRegister usuarioRegister = new UsuarioRegister(
                nombres, appaterno, apmaterno, dni, codigo,
                String.valueOf(idCategoria), String.valueOf(idTipoVehiculo),
                placa, contrasenia
        );

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<MensajeResponse> call = apiService.crearUsuario(usuarioRegister);
        call.enqueue(new Callback<MensajeResponse>() {
            @Override
            public void onResponse(Call<MensajeResponse> call, Response<MensajeResponse> response) {
                if (response.isSuccessful() && response.body() != null){
                    MensajeResponse mensajeResponse = response.body();
                    Toast.makeText(RegistroActivity.this, mensajeResponse.getText(), Toast.LENGTH_SHORT).show();
                    if (mensajeResponse.getStatusCode().equals("201")) {
                        txtnombres.setText("");
                        txtappaterno.setText("");
                        txtapmaterno.setText("");
                        txtdni.setText("");
                        txtcodigo.setText("");
                        txtplaca.setText("");
                        txtcontrasenia.setText("");
                    }
                } else {
                    Toast.makeText(RegistroActivity.this, "Error al registrar el usuario", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MensajeResponse> call, Throwable t) {
                Toast.makeText(RegistroActivity.this, "Error de red: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
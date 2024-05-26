package com.bonifaz.scan_plate.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bonifaz.scan_plate.Models.UsuarioResponse;
import com.bonifaz.scan_plate.R;
import com.bonifaz.scan_plate.Service.ApiService;
import com.bonifaz.scan_plate.Utils.Config;
import com.google.android.material.button.MaterialButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private MaterialButton buttonLogin;
    private MaterialButton buttonRegister;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonRegister = findViewById(R.id.buttonRegister);

        // Configurar Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Verificar las credenciales
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();

                // Realizar la llamada al API
                Call<List<UsuarioResponse>> call = apiService.login(username, password);
                call.enqueue(new Callback<List<UsuarioResponse>>() {
                    @Override
                    public void onResponse(Call<List<UsuarioResponse>> call, Response<List<UsuarioResponse>> response) {
                        if (response.isSuccessful()) {
                            List<UsuarioResponse> usuarioResponseList = response.body();
                            if (!usuarioResponseList.isEmpty()) {
                                // Login exitoso
                                UsuarioResponse usuarioResponse = usuarioResponseList.get(0);
                                String token = usuarioResponse.getToken();
                                String nombrePrivilegio = usuarioResponse.getNombrePrivilegio();

                                // Guardar el token en SharedPreferences
                                SharedPreferences preferences = getSharedPreferences("session", MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("token", token);
                                editor.putString("documento", usuarioResponse.getDocumento());
                                editor.putString("codigo", usuarioResponse.getCodigo());
                                editor.putString("nombres", usuarioResponse.getNombres());
                                editor.putString("appaterno", usuarioResponse.getAppaterno());
                                editor.putString("apmaterno", usuarioResponse.getApmaterno());
                                editor.putString("placa", usuarioResponse.getPlaca());
                                editor.putString("vehiculo", usuarioResponse.getVehiculo());
                                editor.putString("contrasenia", usuarioResponse.getContrasenia());
                                editor.putString("nombrePrivilegio", usuarioResponse.getNombrePrivilegio());
                                editor.apply();

                                // Redirigir según el privilegio
                                if (nombrePrivilegio.equals("SEGURIDAD") || nombrePrivilegio.equals("ADMINISTRADOR")) {
                                    Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                                    startActivity(intent);
                                } else if (nombrePrivilegio.equals("NATURAL")) {
                                    Intent intent = new Intent(LoginActivity.this, Perfil2Activity.class);
                                    startActivity(intent);
                                }
                            } else {
                                // Usuario no encontrado o credenciales incorrectas
                                Toast.makeText(LoginActivity.this, "Usuario no encontrado o credenciales incorrectas", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // Error en la respuesta del servidor
                            Toast.makeText(LoginActivity.this, "Error en la respuesta del servidor", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<UsuarioResponse>> call, Throwable t) {
                        // Error en la solicitud
                        Toast.makeText(LoginActivity.this, "Error en la solicitud", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
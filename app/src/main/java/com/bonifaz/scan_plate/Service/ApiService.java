package com.bonifaz.scan_plate.Service;

import com.bonifaz.scan_plate.Models.Categoria;
import com.bonifaz.scan_plate.Models.CocheraResponse;
import com.bonifaz.scan_plate.Models.EstadoResponse;
import com.bonifaz.scan_plate.Models.MensajeResponse;
import com.bonifaz.scan_plate.Models.PlacaResponse;
import com.bonifaz.scan_plate.Models.TipoVehiculo;
import com.bonifaz.scan_plate.Models.UsuarioRegister;
import com.bonifaz.scan_plate.Models.UsuarioResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ApiService {

    @FormUrlEncoded
    @POST("auth")
    Call<List<UsuarioResponse>> login(
      @Field("DOCUMENTO") String documento,
      @Field("CONTRASENIA") String contrasenia
    );

    @POST("usuario/natural")
    Call<MensajeResponse> crearUsuario(@Body UsuarioRegister usuarioRegister);

    @GET("select/categoria")
    Call<List<Categoria>> getCategorias();

    @GET("select/vehiculo")
    Call<List<TipoVehiculo>> getTiposVehiculo();

    @FormUrlEncoded
    @POST("placa/buscar")
    Call<List<PlacaResponse>> buscarPlaca(@Field("PLACA") String placa);

    @FormUrlEncoded
    @POST("placa/insertar")
    Call<MensajeResponse> insertarPlacaEscaneada(
            @Field("PLACA") String placa
    );

    @FormUrlEncoded
    @POST("placa/salida")
    Call<MensajeResponse> salidaPlaca(
            @Field("PLACA") String placa
    );

    @GET("cochera")
    Call<List<CocheraResponse>> obtenerDatosCochera();

    @FormUrlEncoded
    @POST("placa/estado")
    Call<EstadoResponse> obtenerEstadoPlaca(@Field("PLACA") String placa);


}

package com.bonifaz.scan_plate.Models;

import com.google.gson.annotations.SerializedName;

public class PlacaResponse {

    @SerializedName("DNI")
    private String dni;

    @SerializedName("CODIGO")
    private String codigo;

    @SerializedName("APPATERNO")
    private String appaterno;

    @SerializedName("APMATERNO")
    private String apmaterno;

    @SerializedName("NOMBRES")
    private String nombres;

    @SerializedName("NOM_TIPO_VEHICULO")
    private String nomTipoVehiculo;

    @SerializedName("PLACA")
    private String placa;

    @SerializedName("Mensaje")
    private String mensaje;

    // Getters and setters

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getAppaterno() {
        return appaterno;
    }

    public void setAppaterno(String appaterno) {
        this.appaterno = appaterno;
    }

    public String getApmaterno() {
        return apmaterno;
    }

    public void setApmaterno(String apmaterno) {
        this.apmaterno = apmaterno;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getNomTipoVehiculo() {
        return nomTipoVehiculo;
    }

    public void setNomTipoVehiculo(String nomTipoVehiculo) {
        this.nomTipoVehiculo = nomTipoVehiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
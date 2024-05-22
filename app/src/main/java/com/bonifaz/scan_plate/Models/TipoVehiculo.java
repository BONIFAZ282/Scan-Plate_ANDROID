package com.bonifaz.scan_plate.Models;

import com.google.gson.annotations.SerializedName;

public class TipoVehiculo {

    @SerializedName("ID_TIPO_VEHICULO")
    private int idTipoVehiculo;

    @SerializedName("NOM_TIPO_VEHICULO")
    private String nombre;

    @SerializedName("ESTADO")
    private String estado;


    public int getIdTipoVehiculo() {
        return idTipoVehiculo;
    }

    public void setIdTipoVehiculo(int idTipoVehiculo) {
        this.idTipoVehiculo = idTipoVehiculo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
package com.bonifaz.scan_plate.Models;

import com.google.gson.annotations.SerializedName;

public class Categoria {

    @SerializedName("ID_CATEGORIA")
    private int idCategoria;

    @SerializedName("NOM_CATEGORIA")
    private String nombre;

    @SerializedName("ESTADO")
    private String estado;

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
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
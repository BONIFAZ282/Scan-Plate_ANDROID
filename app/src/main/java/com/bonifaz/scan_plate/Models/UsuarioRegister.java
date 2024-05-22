package com.bonifaz.scan_plate.Models;

import com.google.gson.annotations.SerializedName;

public class UsuarioRegister {

    @SerializedName("NOMBRES")
    private String nombres;

    @SerializedName("APPATERNO")
    private String appterno;

    @SerializedName("APMATERNO")
    private String apmaterno;

    @SerializedName("DOCUMENTO")
    private String documento;

    @SerializedName("CODIGO")
    private String codigo;

    @SerializedName("ID_CATEGORIA")
    private String idcategoria;

    @SerializedName("ID_TIPO_VEHICULO")
    private String idtipovehiculo;

    @SerializedName("PLACA")
    private String placa;

    @SerializedName("CONTRASENIA")
    private String contrasenia;

    public UsuarioRegister(String nombres, String appterno, String apmaterno, String documento, String codigo, String idcategoria, String idtipovehiculo, String placa, String contrasenia) {
        this.nombres = nombres;
        this.appterno = appterno;
        this.apmaterno = apmaterno;
        this.documento = documento;
        this.codigo = codigo;
        this.idcategoria = idcategoria;
        this.idtipovehiculo = idtipovehiculo;
        this.placa = placa;
        this.contrasenia = contrasenia;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getAppterno() {
        return appterno;
    }

    public void setAppterno(String appterno) {
        this.appterno = appterno;
    }

    public String getApmaterno() {
        return apmaterno;
    }

    public void setApmaterno(String apmaterno) {
        this.apmaterno = apmaterno;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(String idcategoria) {
        this.idcategoria = idcategoria;
    }

    public String getIdtipovehiculo() {
        return idtipovehiculo;
    }

    public void setIdtipovehiculo(String idtipovehiculo) {
        this.idtipovehiculo = idtipovehiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}

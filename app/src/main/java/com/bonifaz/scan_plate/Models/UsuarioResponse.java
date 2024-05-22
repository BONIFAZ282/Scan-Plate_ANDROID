package com.bonifaz.scan_plate.Models;

import com.google.gson.annotations.SerializedName;

public class UsuarioResponse {

    @SerializedName("DOCUMENTO")
    private String documento;
    @SerializedName("CODIGO")
    private String codigo;
    @SerializedName("NOMBRES")
    private String nombres;
    @SerializedName("CONTRASENIA")
    private String contrasenia;
    @SerializedName("NOMBRE_PRIVILEGIO")
    private String nombrePrivilegio;
    @SerializedName("token")
    private String token;

    // Getters and Setters

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

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getNombrePrivilegio() {
        return nombrePrivilegio;
    }

    public void setNombrePrivilegio(String nombrePrivilegio) {
        this.nombrePrivilegio = nombrePrivilegio;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

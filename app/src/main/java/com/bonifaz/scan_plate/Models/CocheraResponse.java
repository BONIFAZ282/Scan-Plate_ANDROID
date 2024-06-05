package com.bonifaz.scan_plate.Models;

public class CocheraResponse {
    private String NOM_ESTACIONAMIENTO;
    private int CANTIDAD_TOTAL;
    private int CANTIDAD_DISPONIBLE;

    public String getNOM_ESTACIONAMIENTO() {
        return NOM_ESTACIONAMIENTO;
    }

    public void setNOM_ESTACIONAMIENTO(String NOM_ESTACIONAMIENTO) {
        this.NOM_ESTACIONAMIENTO = NOM_ESTACIONAMIENTO;
    }

    public int getCANTIDAD_TOTAL() {
        return CANTIDAD_TOTAL;
    }

    public void setCANTIDAD_TOTAL(int CANTIDAD_TOTAL) {
        this.CANTIDAD_TOTAL = CANTIDAD_TOTAL;
    }

    public int getCANTIDAD_DISPONIBLE() {
        return CANTIDAD_DISPONIBLE;
    }

    public void setCANTIDAD_DISPONIBLE(int CANTIDAD_DISPONIBLE) {
        this.CANTIDAD_DISPONIBLE = CANTIDAD_DISPONIBLE;
    }
}

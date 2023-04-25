package com.uco.stloan.model;

public enum Rol {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER");

    private String valor;

    private Rol(String valor) {
        this.valor = valor; 
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

}

package com.mycompany.ej1;

/* @author DAM217 */


public class Futbolista {
    private int identificador;
    private String alias;
    private String codigo;
    private Puesto puesto;
    private float altura;

    public Futbolista() {
    }
    
    public Futbolista(int identificador, String alias, String codigo, Puesto puesto, float altura) {
        this.identificador = identificador;
        this.alias = alias;
        this.codigo = codigo;
        this.puesto = puesto;
        this.altura = altura;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Puesto getPuesto() {
        return puesto;
    }

    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }
    
    
}

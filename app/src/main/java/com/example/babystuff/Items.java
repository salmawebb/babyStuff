package com.example.babystuff;

import java.io.Serializable;

public class Items implements Serializable {
    private int id;
    private String nombre;
    private int cantidad;
    private double precio;
    private byte[] imagen;

    public Items(int id, byte[] imagen, String nombre, int cantidad, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.imagen = imagen;
    }

    public Items(){

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public byte[] getImagen() {
        return imagen;
    }
}

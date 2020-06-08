package com.example.babystuff.entidades;

public class Store {
    private int id;
    private byte []  imagen;
    private String nombre;
    private int cantidad;
    private double precio;

    public Store(int id, byte[] imagen, String nombre, int cantidad, double precio) {
        this.id = id;
        this.imagen = imagen;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
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

    public int getId() {
        return id;
    }

    public byte[] getImagen() {
        return imagen;
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


}

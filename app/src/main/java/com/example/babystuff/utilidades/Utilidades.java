package com.example.babystuff.utilidades;

public class Utilidades {
    public static final String TABLA_STORE = "store";
    public static final String CAMPO_ID = "id"; //0
    public static final String CAMPO_IMAGEN = "Imagen"; //1
    public static final String CAMPO_NOMBRE = "Nombre";//2
    public static final String CAMPO_CANTIDAD = "Cantidad";//3
    public static final String CAMPO_PRECIO = "Precio";//4



    public static final String CREATE_TABLE_STORE = "CREATE TABLE "+ TABLA_STORE +
            "(" + CAMPO_ID +" INTEGER," +
            CAMPO_IMAGEN +" BLOB," +
            CAMPO_NOMBRE +" TEXT,"+
            CAMPO_CANTIDAD +" INTEGER,"+
            CAMPO_PRECIO+ " REAL)";
}

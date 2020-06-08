package com.example.babystuff;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.babystuff.utilidades.Utilidades;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {


    public ConexionSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utilidades.CREATE_TABLE_STORE); //ejecutar el query
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS db_store");
        onCreate(db);
    }

    public void queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public void insertData(String id, byte[] imagen , String nombre, int cantidad, double precio ){
        SQLiteDatabase database = getWritableDatabase();
       /* String sql = "INSERT INTO " +  Utilidades.TABLA_STORE + " VALUES (?,?,?,?,?); " ;
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(0,id);
        statement.bindBlob(1,imagen);
        statement.bindString(2,nombre);
        statement.bindLong(3,cantidad);
        statement.bindDouble(4,precio);

        statement.executeInsert();*/

        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_ID, id); //insertarlo en la tabla
        values.put(Utilidades.CAMPO_NOMBRE, nombre);
        values.put(Utilidades.CAMPO_CANTIDAD, cantidad);
        values.put(Utilidades.CAMPO_PRECIO, precio);
        values.put(Utilidades.CAMPO_IMAGEN, imagen);

        Long idResult = database.insert(Utilidades.TABLA_STORE, Utilidades.CAMPO_ID,values);


        database.close();
    }

    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }


    public void buscarR(Items item,String nombre){
        SQLiteDatabase db =getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_STORE+ " WHERE " + Utilidades.CAMPO_NOMBRE+ "="+ nombre+"'",null);
        List<Items> nombres = new ArrayList<>();
        if (cursor.moveToFirst()){
            do {
                item.setId(cursor.getInt(cursor.getColumnIndex(Utilidades.CAMPO_ID)));
                item.setImagen(cursor.getBlob(cursor.getColumnIndex(Utilidades.CAMPO_IMAGEN)));
                item.setNombre( cursor.getString(cursor.getColumnIndex(Utilidades.CAMPO_NOMBRE)));
                item.setCantidad( cursor.getInt(cursor.getColumnIndex(Utilidades.CAMPO_CANTIDAD)));
                item.setPrecio( cursor.getDouble( cursor.getColumnIndex(Utilidades.CAMPO_PRECIO)));
            }while (cursor.moveToNext());
        }
    }

}

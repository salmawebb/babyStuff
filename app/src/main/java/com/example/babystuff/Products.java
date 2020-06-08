package com.example.babystuff;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.babystuff.utilidades.Utilidades;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Products extends AppCompatActivity {
    EditText  nombre, cantidad, precio, id;
    ImageView imagen;
    Button agregar, choose;
    ImageButton home, search;

    final int REQUEST_CODE_GALERY = 999;
    public static ConexionSQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        init();

        sqLiteHelper = new ConexionSQLiteHelper(this, "db_store", null, 1);
        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS store (" +
                Utilidades.CAMPO_ID +" INTEGER," +
                Utilidades.CAMPO_IMAGEN +" BLOB," +
                Utilidades.CAMPO_NOMBRE +" TEXT,"+
                Utilidades.CAMPO_CANTIDAD +" INTEGER,"+
                Utilidades.CAMPO_PRECIO+ " REAL)");

        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        Products.this,
                        new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALERY
                );
            }
        });

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    sqLiteHelper.insertData(
                            id.getText().toString().trim(),
                            imageViewToByte(imagen),
                            nombre.getText().toString().trim(),
                            Integer.parseInt(cantidad.getText().toString().trim()),
                            Double.parseDouble(precio.getText().toString().trim())

                    );
                    Toast.makeText(getApplicationContext(), "Ingresado a la base de datos", Toast.LENGTH_SHORT).show();
                    id.setText("");
                    imagen.setImageResource(R.mipmap.ic_launcher);
                    nombre.setText("");
                    cantidad.setText("");
                    precio.setText("");
                }catch (Exception e){
                    e.printStackTrace();

                }


            }
        });

        home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Products.this, ItemList.class);
                startActivity(intent);
            }
        });

        search.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Products.this, Search.class);
                startActivity(intent);
            }
        });


    }



    private byte[] imageViewToByte(ImageView image){
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_CODE_GALERY) {
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALERY);
            } else{
                Toast.makeText(getApplicationContext(), "You don't have permissions to access", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_GALERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imagen.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                Toast.makeText(getApplicationContext(), "Verifica lo que escribiste", Toast.LENGTH_SHORT).show();
                e.printStackTrace();

            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void init(){
        id = (EditText) findViewById(R.id.idproduct);
        nombre = (EditText) findViewById(R.id.idname);
        cantidad = (EditText) findViewById(R.id.idcant);
        precio = (EditText) findViewById(R.id.idprice);
        imagen = (ImageView) findViewById(R.id.idphoto);
        agregar = (Button) findViewById(R.id.btnadd);
        choose = (Button) findViewById(R.id.chooseImg);
        home = (ImageButton) findViewById(R.id.btnhome);
        search = (ImageButton) findViewById(R.id.btnsearch);

    }

    public void delete(View view){
        ConexionSQLiteHelper conection = new ConexionSQLiteHelper(getApplicationContext(), "db_store", null, 1);
        SQLiteDatabase db = conection.getWritableDatabase();
        String[] delete = {id.getText().toString()};
        db.delete(Utilidades.TABLA_STORE, Utilidades.CAMPO_ID+"=?", delete);
        Toast.makeText(getApplicationContext(),"id eliminado "  , Toast.LENGTH_SHORT).show();
        db.close();

        id.setText("");
        imagen.setImageResource(R.mipmap.ic_launcher);
        nombre.setText("");
        cantidad.setText("");
        precio.setText("");

    }

    public void update(View view){
        ConexionSQLiteHelper conection = new ConexionSQLiteHelper(getApplicationContext(), "db_store", null, 1);
        SQLiteDatabase db = conection.getWritableDatabase();
        try {
            String[] update = {id.getText().toString()};
            ContentValues values = new ContentValues();
            values.put(Utilidades.CAMPO_IMAGEN, imageViewToByte(imagen));
            values.put(Utilidades.CAMPO_NOMBRE, nombre.getText().toString());
            values.put(Utilidades.CAMPO_CANTIDAD, cantidad.getText().toString());
            values.put(Utilidades.CAMPO_PRECIO, precio.getText().toString());


            db.update(Utilidades.TABLA_STORE, values, Utilidades.CAMPO_ID + "=?", update);

            //Long idResult = db.insert(Utilidades.TABLA_PETS, Utilidades.CAMPO_ID,values);
            Toast.makeText(getApplicationContext(), "id modificado: " + id.getText().toString(), Toast.LENGTH_SHORT).show();
        }catch (NumberFormatException e){//en caso que el usuario escriba un string en vez de valor numerico en los campos de cantidad y precio
            Toast.makeText(getApplicationContext(), "Verifica lo que escribiste", Toast.LENGTH_SHORT).show();
        }
        db.close();
        id.setText("");
        imagen.setImageResource(R.mipmap.ic_launcher);
        nombre.setText("");
        cantidad.setText("");
        precio.setText("");
    }


    public void buscar(View view) {
        ConexionSQLiteHelper conection = new ConexionSQLiteHelper(getApplicationContext(), "db_store", null, 1);
        SQLiteDatabase db = conection.getReadableDatabase(); // para escribir en la base de datos

        String[] consultar = {Utilidades.CAMPO_IMAGEN, Utilidades.CAMPO_NOMBRE, Utilidades.CAMPO_CANTIDAD, Utilidades.CAMPO_PRECIO};
        String[] idconsultar = {id.getText().toString()};

        try {

            Cursor cursor = db.query(Utilidades.TABLA_STORE, consultar, Utilidades.CAMPO_ID + "=?", idconsultar, null, null, null);
            cursor.moveToFirst();

            byte[] blob = cursor.getBlob(cursor.getColumnIndex(Utilidades.CAMPO_IMAGEN));
            ByteArrayInputStream inputStream = new ByteArrayInputStream(blob);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

            imagen.setImageBitmap(bitmap);
            nombre.setText(cursor.getString(cursor.getColumnIndex(Utilidades.CAMPO_NOMBRE)));
            cantidad.setText(cursor.getString(cursor.getColumnIndex(Utilidades.CAMPO_CANTIDAD)));
            precio.setText(cursor.getString(cursor.getColumnIndex(Utilidades.CAMPO_PRECIO)));

            cursor.close();

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "No Existe", Toast.LENGTH_LONG).show();

        }
    }

    public void shopView(View view){

        Intent intent = new Intent (this, Shop.class);

        startActivity(intent);
    }

    public void searchView(View view){

        Intent intent = new Intent (this, Search.class);

        startActivity(intent);
    }

    public void homeView(View view){

        Intent intent = new Intent (this, ItemList.class);

        startActivity(intent);
    }

}

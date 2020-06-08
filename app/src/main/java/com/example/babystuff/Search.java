package com.example.babystuff;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.babystuff.utilidades.Utilidades;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

public class Search extends AppCompatActivity {
    EditText  nombre, precio, id;
    TextView name, price;
    ImageView imagen;
    Button buscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        buscar = (Button) findViewById(R.id.btnBuscar);

    }


    public void search(View view) {
        nombre = (EditText) findViewById(R.id.idbuscar);
        name= (TextView) findViewById(R.id.productid);
        price = (TextView) findViewById(R.id.precioid);
        imagen = (ImageView) findViewById(R.id.img);

        ConexionSQLiteHelper conection = new ConexionSQLiteHelper(getApplicationContext(), "db_store", null, 1);
        SQLiteDatabase db = conection.getReadableDatabase(); // para escribir en la base de datos

        String[] mostrar = {Utilidades.CAMPO_IMAGEN, Utilidades.CAMPO_NOMBRE, Utilidades.CAMPO_PRECIO};
        String[] consultar = {nombre.getText().toString().trim()};

        try {

            Cursor cursor = db.query(Utilidades.TABLA_STORE, mostrar, Utilidades.CAMPO_NOMBRE + "=?", consultar, null, null, null);
            cursor.moveToFirst();



            byte[] blob = cursor.getBlob(cursor.getColumnIndex(Utilidades.CAMPO_IMAGEN));
            ByteArrayInputStream inputStream = new ByteArrayInputStream(blob);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

            imagen.setImageBitmap(bitmap);
            name.setText(cursor.getString(cursor.getColumnIndex(Utilidades.CAMPO_NOMBRE)));
            //cantidad.setText(cursor.getString(cursor.getColumnIndex(Utilidades.CAMPO_CANTIDAD)));
            price.setText("$"+cursor.getString(cursor.getColumnIndex(Utilidades.CAMPO_PRECIO)));

            cursor.close();

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "no existe", Toast.LENGTH_LONG).show();

        }
    }

    public void productsView(View view){

        Intent intent = new Intent (this, Products.class);

        startActivity(intent);
    }

    public void homeView(View view){

        Intent intent = new Intent (this, ItemList.class);

        startActivity(intent);
    }

    public void shopView(View view){

        Intent intent = new Intent (this, Shop.class);

        startActivity(intent);
    }
}



package com.example.babystuff;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.babystuff.utilidades.Utilidades;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import static com.example.babystuff.R.*;

public class ItemList extends AppCompatActivity {
    GridView gridView;
    ArrayList<Items> list;
    ItemsListAdapter adapter = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.item_list_activity);

        gridView = (GridView) findViewById(id.view);
        list = new ArrayList<Items>();
        adapter = new ItemsListAdapter(this, layout.shop_items,list);

        gridView.setAdapter(adapter);
        //get data from sqlite

        ConexionSQLiteHelper sqLiteHelper = new ConexionSQLiteHelper(this, "db_store", null, 1);
        try {
            Cursor cursor = sqLiteHelper.getData("SELECT * FROM " + Utilidades.TABLA_STORE);
            list.clear();
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                byte[] imagen = cursor.getBlob(1);
                String nombre = cursor.getString(2);
                int cantidad = cursor.getInt(3);
                double precio = cursor.getDouble(4);

                list.add(new Items(id, imagen, nombre, cantidad, precio));
            }

            adapter.notifyDataSetChanged();
        }catch (NullPointerException e){
            e.printStackTrace();
        }


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent (ItemList.this, Shop.class);
                intent.putExtra("Obj", list.get(position));
                startActivity(intent);
            }
        });
    }

    public void productsView(View view){

        Intent intent = new Intent (this, Products.class);

        startActivity(intent);
    }

    public void searchView(View view){

        Intent intent = new Intent (this, Search.class);

        startActivity(intent);
    }

    public void shopView(View view){

        Intent intent = new Intent (this, Shop.class);

        startActivity(intent);
    }


}

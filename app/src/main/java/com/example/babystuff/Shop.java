package com.example.babystuff;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.babystuff.utilidades.Utilidades;

import java.io.ByteArrayInputStream;

public class Shop extends AppCompatActivity {
    private Items item;
    private TextView nombre, precio;
    private ImageView imagen;
    private EditText totalPrice;
    private Button pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        pay = (Button) findViewById(R.id.pay);
        totalPrice = (EditText) findViewById(R.id.totalPrice);
        totalPrice.setEnabled(false);

        item = (Items) getIntent().getSerializableExtra("Obj");

        nombre = (TextView) findViewById(R.id.producto);
        precio = (TextView) findViewById(R.id.price);
        imagen = (ImageView) findViewById(R.id.productImagen);


        try {
            nombre.setText(item.getNombre());
            precio.setText(String.valueOf(item.getPrecio()));
            String tp = precio.getText().toString();
            totalPrice.setText(tp);
            byte[] blob = item.getImagen();
            ByteArrayInputStream inputStream = new ByteArrayInputStream(blob);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            imagen.setImageBitmap(bitmap);
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Shop.this, "Pagado", Toast.LENGTH_SHORT).show();
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

    public void homeView(View view){

        Intent intent = new Intent (this, ItemList.class);

        startActivity(intent);
    }

    public void setTotalPrice(){
        /*ciclo para que sume todos los valores totales
        convertir el valor recibido a int para hacer calculos y
        despues volverlo a pasar a string para insertarlo en el campo.
         */

        totalPrice.setText(String.valueOf(precio));

    }


}

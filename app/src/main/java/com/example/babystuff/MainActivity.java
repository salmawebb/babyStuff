package com.example.babystuff;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import com.example.babystuff.utilidades.Utilidades;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

   // private static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent (this, ItemList.class);

        startActivity(intent);
    }
}

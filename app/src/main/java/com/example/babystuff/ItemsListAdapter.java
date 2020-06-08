package com.example.babystuff;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemsListAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Items> itemsList;

    public ItemsListAdapter(Context context, int layout, ArrayList<Items> itemsList) {
        this.context = context;
        this.layout = layout;
        this.itemsList = itemsList;
    }

    @Override
    public int getCount() {
        return itemsList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder{
        ImageView imageView;
        TextView nombre, precio;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View row = view;
        ViewHolder holder = new ViewHolder();
        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.nombre = (TextView) row.findViewById(R.id.itemName);
            holder.precio = (TextView) row.findViewById(R.id.itemPrice);
            holder.imageView = (ImageView) row.findViewById(R.id.shopImg);
            row.setTag(holder);

        }else{
            holder = (ViewHolder) row.getTag();
        }

        Items items = itemsList.get(position);

        holder.nombre.setText(items.getNombre());
        holder.precio.setText(String.valueOf(items.getPrecio()));

        byte[] itemImage = items.getImagen();
        Bitmap bitmap = BitmapFactory.decodeByteArray(itemImage, 0, itemImage.length);
        holder.imageView.setImageBitmap(bitmap);
        return row;
    }
}

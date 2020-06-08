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
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecycleViewAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Items> itemsList;

    public RecycleViewAdapter(Context context, int layout, ArrayList<Items> itemsList) {
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

   /* private class ViewHolder{
        ImageView imageView;
        TextView nombre, precio;
    }*/

   private class ViewHolder{
       ImageView imageView;
       TextView nombre, precio;
   }


    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View row = view;
        RecycleViewAdapter.ViewHolder holder = new RecycleViewAdapter.ViewHolder();
        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.nombre = (TextView) row.findViewById(R.id.itemName);
            holder.precio = (TextView) row.findViewById(R.id.itemPrice);
            holder.imageView = (ImageView) row.findViewById(R.id.shopImg);
            row.setTag(holder);

        }else{
            holder = (RecycleViewAdapter.ViewHolder) row.getTag();
        }

        Items items = itemsList.get(position);

        holder.nombre.setText(items.getNombre());
        holder.precio.setText(String.valueOf(items.getPrecio()));

        byte[] itemImage = items.getImagen();
        Bitmap bitmap = BitmapFactory.decodeByteArray(itemImage, 0, itemImage.length);
        holder.imageView.setImageBitmap(bitmap);
        return row;
    }

   /* public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView producto, precio;
        ImageView imgproduct;

        public ViewHolder(View itemView){
            super(itemView);
            producto = (TextView) itemView.findViewById(R.id.productid);
            precio = (TextView) itemView.findViewById(R.id.priceid);
            imgproduct = (ImageView) itemView.findViewById(R.id.img);
        }
    }

    public List<Items> item;

    public RecycleViewAdapter(List<Items> item) {
        this.item = item;
    }


    @NonNull
    @Override
    public RecycleViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row,parent, false);
        ViewHolder viewHolder= new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewAdapter.ViewHolder holder, int position) {
        Items items = item.get(position);

        byte[] itemImage = items.getImagen();
        Bitmap bitmap = BitmapFactory.decodeByteArray(itemImage, 0, itemImage.length);

        holder.producto.setText(item.get(position).getNombre());
        holder.precio.setText((int) item.get(position).getPrecio());
        holder.imgproduct.setImageBitmap(bitmap);

    }

    @Override
    public int getItemCount() {
        return item.size();
    }*/
}

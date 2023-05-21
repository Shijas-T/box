package com.example.box;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BrowseItemAdapter extends RecyclerView.Adapter<BrowseItemAdapter.ViewHolder> {

    //Initialise the list item here
    private ArrayList<ProductModel> arrayListAllItem = new ArrayList<>();
    //Creating context for toast
    private Context context;

    private static final String TAG = "BrowseItemAdapter";

    public BrowseItemAdapter(ArrayList<ProductModel> arrayListAllItem, Context context) {
        this.arrayListAllItem = arrayListAllItem;
        this.context = context;
    }

    //View holder(it calls the created recycler View)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;

    }

    //All the click listener is done here
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //set text view
        holder.textViewProductName.setText(arrayListAllItem.get(position).getProductName());
        holder.textViewProductDescription.setText(arrayListAllItem.get(position).getProductDescription());
        holder.textViewProductPrice.setText("₹" + arrayListAllItem.get(position).getProductPrice());
        Picasso.with(context).load(arrayListAllItem.get(position).getProductImageUrl() == null ? null : arrayListAllItem.get(position).getProductImageUrl())
                .placeholder(R.mipmap.ic_lap_stock)
                .error(R.mipmap.ic_lap_stock)
                .into(holder.imageViewProductImage);
        //        Log.e(TAG, "onBindViewHolder: "+ arrayListAllItem.get(position).getProductImageUrl());

        //On click listener for the recycler view
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context,arrayListAllItem.get(position).getProductName()+"\tselected",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, ProductInDetailActivity.class);
                intent.putExtra("product", arrayListAllItem.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayListAllItem.size();
    }

    public void setArrayListAllItem(ArrayList<ProductModel> arrayListAllItem) {
        this.arrayListAllItem = arrayListAllItem;
        notifyDataSetChanged();
    }

    //Every view inside the recycler view is declared and initialised here
    public class ViewHolder extends RecyclerView.ViewHolder{

        //Declaration
        private TextView textViewProductName, textViewProductDescription, textViewProductPrice;
        private ImageView imageViewProductImage;
        private LinearLayout parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewProductName =  itemView.findViewById(R.id.itemName);
            textViewProductDescription =  itemView.findViewById(R.id.itemDescription);
            textViewProductPrice =  itemView.findViewById(R.id.itemPrice);
            imageViewProductImage =  itemView.findViewById(R.id.itemIcon);

            //The container
            parent = itemView.findViewById(R.id.single_list_item);
        }
    }
}
